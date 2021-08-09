package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.RefreshToken;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.exception.custom.CustomUnauthorizedException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.JwtRequest;
import com.gasstation.managementsystem.model.dto.RefreshTokenDTO;
import com.gasstation.managementsystem.model.dto.token.JwtResponseLogin;
import com.gasstation.managementsystem.model.dto.token.JwtResponseRefresh;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.repository.RefreshTokenRepository;
import com.gasstation.managementsystem.utils.JwtTokenUtil;
import com.gasstation.managementsystem.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
@Tag(name = "Authentication", description = "API for Authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserService userService;

    private final RefreshTokenRepository refreshTokenRepository;


    @Operation(summary = "Login to Application")
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDTO userDTO = userService.findByUserName(authenticationRequest.getUsername());

        if (!userDTO.getActive()) {
            throw new CustomUnauthorizedException(CustomError.builder().code("unauthorized")
                    .message("Access denied, you are deactivate").build());
        }

        final String accessToken = jwtTokenUtil.generateToken(userDTO.getUsername(), JwtTokenUtil.ACCESS_TOKEN_EXPIRED);
        final String refreshToken = jwtTokenUtil.generateToken(userDTO.getUsername(), JwtTokenUtil.REFRESH_TOKEN_EXPIRED);
        refreshTokenRepository.save(RefreshToken.builder()
                .refreshToken(refreshToken)
                .user(User.builder().id(userDTO.getId()).build()).build());
        return ResponseEntity.ok(new JwtResponseLogin(accessToken, refreshToken));
    }


    @Operation(summary = "View profile of user logged in")
    @GetMapping("/profile")
    public UserDTO profile(Principal principal) {
        return userService.findByUserName(principal.getName());
    }

    @Operation(summary = "Refresh token")
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refresh(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO)
            throws CustomUnauthorizedException, CustomBadRequestException, CustomNotFoundException {
        String refreshTokenFromClient = refreshTokenDTO.getRefreshToken();
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findById(refreshTokenFromClient);
        if (refreshTokenOptional.isPresent()) {
            refreshTokenRepository.delete(refreshTokenOptional.get());
            String username;
            try {
                username = jwtTokenUtil.getUsernameFromToken(refreshTokenFromClient);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
                throw new CustomBadRequestException(CustomError.builder()
                        .code("unable.get")
                        .message("Unable to get Refresh Token")
                        .field("refreshToken")
                        .build());
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                throw new CustomBadRequestException(CustomError.builder()
                        .code("token.expired")
                        .message("Refresh Token has expired")
                        .field("refreshToken")
                        .build());
            } catch (Exception e) {
                System.out.println("refresh has something error");
                throw new CustomBadRequestException(CustomError.builder()
                        .code("anything")
                        .message("refresh has something error : \n" + e.getMessage())
                        .field("refreshToken")
                        .build());
            }

            UserDTO userDTO = userService.findByUserName(username);
            if (!userDTO.getActive()) {
                throw new CustomUnauthorizedException(CustomError.builder().code("unauthorized")
                        .message("Access denied, you are deactive").build());
            }
            final String accessToken = jwtTokenUtil.generateToken(username, JwtTokenUtil.ACCESS_TOKEN_EXPIRED);
            final String refreshToken = jwtTokenUtil.generateToken(username, JwtTokenUtil.REFRESH_TOKEN_EXPIRED);
            refreshTokenRepository.save(RefreshToken.builder()
                    .refreshToken(refreshToken)
                    .user(User.builder().id(userDTO.getId()).build()).build());
            return ResponseEntity.ok(new JwtResponseRefresh(accessToken, refreshToken, userDTO));
        } else {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.exist").field("refreshToken").message("Refresh token not exist").build());
        }
    }

    @Operation(summary = "delete RefreshToken")
    @DeleteMapping("/refresh-token")
    public void delete(HttpServletRequest request) throws CustomNotFoundException, CustomBadRequestException {
        final String refreshTokenFromHeader = request.getHeader("RefreshToken");
        String refreshToken;
        if (refreshTokenFromHeader != null && refreshTokenFromHeader.startsWith("Bearer ")) {
            refreshToken = refreshTokenFromHeader.substring(7).trim();
            Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findById(refreshToken);
            if (refreshTokenOptional.isPresent()) {
                refreshTokenRepository.delete(refreshTokenOptional.get());
            } else {
                throw new CustomNotFoundException(CustomError.builder()
                        .code("not.exist").field("refreshToken").message("Refresh token not exist").build());
            }
        } else {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("invalid").field("refreshToken").message("Refresh token invalid").build());
        }


    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new CustomUnauthorizedException(CustomError.builder().code("unauthorized").message("Unauthorized").build());
        }
    }
}
