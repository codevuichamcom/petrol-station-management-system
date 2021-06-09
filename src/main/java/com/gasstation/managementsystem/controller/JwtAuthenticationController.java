package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.model.JwtRequest;
import com.gasstation.managementsystem.model.JwtResponse;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.security.jwt.JwtTokenUtil;
import com.gasstation.managementsystem.service.AccountService;
import com.gasstation.managementsystem.service.UserService;
import com.gasstation.managementsystem.service.impl.JwtUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
@Tag(name = "Authentication", description = "API for Authentication")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;


//    @PostMapping(value = "/register")
//    public AccountDTO register(@RequestBody Account account) throws Exception {
//        return accountService.save(account);
//    }

    @Operation(summary = "Login to Application")
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
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
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
