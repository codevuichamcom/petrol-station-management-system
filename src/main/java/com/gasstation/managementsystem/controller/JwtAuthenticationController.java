package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.AcceptToken;
import com.gasstation.managementsystem.exception.custom.CustomUnauthorizedException;
import com.gasstation.managementsystem.model.JwtRequest;
import com.gasstation.managementsystem.model.JwtResponse;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.security.jwt.JwtTokenUtil;
import com.gasstation.managementsystem.service.AcceptTokenService;
import com.gasstation.managementsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
@Tag(name = "Authentication", description = "API for Authentication")
@RequiredArgsConstructor
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserService userService;

    private final AcceptTokenService acceptTokenService;


    @Operation(summary = "Login to Application")
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDTO userDTO = userService.findByUserName(authenticationRequest.getUsername());

        if (!userDTO.getActive()) {
            throw new CustomUnauthorizedException("Access denied");
        }

        final String token = jwtTokenUtil.generateToken(userDTO);

        AcceptToken acceptToken = AcceptToken.builder().token(token).userId(userDTO.getId()).build();
        acceptTokenService.save(acceptToken);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Operation(summary = "Delete Accept token by token")
    @DeleteMapping("/delete-accept-token")
    public void deleteAcceptToken(@RequestParam(name = "token", required = false) String token,
                                  @RequestParam(name = "accountId", required = false) Integer accountId) {
        if (token != null) {
            acceptTokenService.deleteByToken(token);
        } else {
            if (accountId != null) {
                acceptTokenService.deleteByAccountId(accountId);
            }
        }
    }

    @Operation(summary = "View profile of user logined")
    @GetMapping("/profile")
    public UserDTO profile(Principal principal) {
        return userService.findByUserName(principal.getName());
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new CustomUnauthorizedException("Unauthorized");
        }
    }
}
