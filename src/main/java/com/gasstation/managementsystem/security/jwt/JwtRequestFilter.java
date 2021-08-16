package com.gasstation.managementsystem.security.jwt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.repository.ApiRepository;
import com.gasstation.managementsystem.repository.UserRepository;
import com.gasstation.managementsystem.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/*
The JwtRequestFilter extends the Spring Web Filter OncePerRequestFilter class. For any incoming request this Filter
class gets executed. It checks if the request has a valid JWT token. If it has a valid JWT Token then it sets the
 Authentication in the context, to specify that the current user is authenticated.
 */
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    private final UserRepository userRepository;

    private final ApiRepository apiRepository;
    private final List<String> listDontNeedAuthentication = Arrays.asList("/api/v1/login", "/api/v1/refresh-token");

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain chain) throws ServletException, IOException {

        String url = request.getRequestURI();
        String methodRequest = request.getMethod().toUpperCase();
        if (listDontNeedAuthentication.stream().anyMatch(o -> o.equals(url))) {
            chain.doFilter(request, response);
            return;
        }
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String accessToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            accessToken = requestTokenHeader.substring(7).trim();
            try {
                username = jwtTokenUtil.getUsernameFromToken(accessToken);
            } catch (SignatureException e) {
                System.out.println("Invalid jwt signature");
            } catch (MalformedJwtException e) {
                System.out.println("Invalid JWT Token");
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                CustomError customError = CustomError.builder().code("token.expired").field("accessToken").message("JWT Token has expired").build();
                responseToClient(response, customError, HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = userRepository.findByUsername(username);
            UserType userType = user.getUserType();
            if (needCheckPermission(userType, url, methodRequest)) {
                String path = request.getRequestURI().toLowerCase();
                if (path.matches("/api/v1/.+")) {
                    String route = path.split("[/]")[3];
                    path = Api.PREFIX + "/" + route;
                }

                Optional<Api> apiOptional = apiRepository.findByPathAndMethod(path, methodRequest);
                if (apiOptional.isEmpty()) {
                    CustomError customError = CustomError.builder().code("access.denied").message("Access denied, You have no permission").table("permission_tbl").build();
                    responseToClient(response, customError, HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
                Set<UserType> userTypeList = apiOptional.get().getUserTypeList();

                boolean permission = false;
                for (UserType u : userTypeList) {
                    if (u.getId() == userType.getId()) {
                        permission = true;
                        break;
                    }
                }
                if (!permission) {
                    CustomError customError = CustomError.builder().code("access.denied").message("Access denied, You have no permission").table("permission_tbl").build();
                    responseToClient(response, customError, HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            }

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(accessToken, user)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    private boolean needCheckPermission(UserType userType, String url, String method) {
        if (url.startsWith("/api/v1/shifts") && method.equalsIgnoreCase("GET") && userType.getId() == UserType.OWNER)
            return false;
        if (url.startsWith("/api/v1/fuels") && method.equalsIgnoreCase("GET") && userType.getId() == UserType.OWNER)
            return false;
        if (url.startsWith("/api/v1/user-types") && method.equalsIgnoreCase("GET") && userType.getId() == UserType.OWNER)
            return false;
        if (url.startsWith("/api/v1/apis") && method.equalsIgnoreCase("GET"))
            return false;
        if (url.startsWith("/api/v1/profile") && method.equalsIgnoreCase("GET"))
            return false;
        return userType.getId() != UserType.ADMIN;
    }

    private void responseToClient(HttpServletResponse response, CustomError customError, int httpStatus) throws IOException {
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.setStatus(httpStatus);
        Map<String, CustomError> map = new HashMap<>();
        map.put("error", customError);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        response.getOutputStream().print(mapper.writeValueAsString(map));
        response.flushBuffer();
    }
}
