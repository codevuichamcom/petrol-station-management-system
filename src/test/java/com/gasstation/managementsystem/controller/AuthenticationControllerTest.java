package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.RefreshToken;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomUnauthorizedException;
import com.gasstation.managementsystem.model.JwtRequest;
import com.gasstation.managementsystem.model.dto.token.JwtResponseLogin;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.repository.RefreshTokenRepository;
import com.gasstation.managementsystem.utils.JwtTokenUtil;
import com.gasstation.managementsystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthenticationControllerTest {
    @Mock
    JwtTokenUtil jwtTokenUtil;
    @Mock
    UserService userService;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    RefreshTokenRepository refreshTokenRepository;
    @InjectMocks
    AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Access denied
     */

    @Test
    void createAuthenticationToken_UTCID01() {
        JwtRequest authenticationRequest = new JwtRequest();
        authenticationRequest.setUsername("userName");
        authenticationRequest.setPassword("passWord");
        UserDTO userDTO = UserDTO.builder().id(1).username("userName").active(false).build();
        Mockito.when(userService.findByUserName("userName")).thenReturn(userDTO);
        assertThrows(CustomUnauthorizedException.class, () -> {
            authenticationController.createAuthenticationToken(authenticationRequest);
        });
    }

    /**
     * Access success
     */

    @Test
    void createAuthenticationToken_UTCID02() throws Exception {
        JwtRequest authenticationRequest = new JwtRequest();
        authenticationRequest.setUsername("userName");
        authenticationRequest.setPassword("passWord");
        UserDTO userDTO = UserDTO.builder().id(1).username("userName").active(true).build();
        Mockito.when(userService.findByUserName("userName")).thenReturn(userDTO);
        final String accessToken = jwtTokenUtil.generateToken(userDTO.getUsername(), JwtTokenUtil.ACCESS_TOKEN_EXPIRED);
        final String refreshToken = jwtTokenUtil.generateToken(userDTO.getUsername(), JwtTokenUtil.REFRESH_TOKEN_EXPIRED);
        RefreshToken refreshTokenClass = RefreshToken.builder().refreshToken(refreshToken).user(User.builder().id(userDTO.getId()).build()).build();

        ResponseEntity mockResult = ResponseEntity.ok(new JwtResponseLogin(accessToken, refreshToken));
        Mockito.when(refreshTokenRepository.save(Mockito.any(RefreshToken.class))).thenReturn(refreshTokenClass);
        authenticationController.createAuthenticationToken(authenticationRequest);
    }

    @Test
    void profile() {
//        UserDTO userDTO = UserDTO.builder().id(1).username("userName").active(true).build();
//        final String accessToken = jwtTokenUtil.generateToken(userDTO.getUsername(), JwtTokenUtil.ACCESS_TOKEN_EXPIRED);
//        final String refreshToken = jwtTokenUtil.generateToken(userDTO.getUsername(), JwtTokenUtil.REFRESH_TOKEN_EXPIRED);
//        RefreshToken refreshTokenClass = RefreshToken.builder().refreshToken(refreshToken).user(User.builder().id(userDTO.getId()).build()).build();
//        Optional<RefreshToken> refreshTokenOptional = Optional.of(refreshTokenClass);
//        Mockito.when(refreshTokenRepository.findById(refreshTokenClass.getRefreshToken()))
//                .thenReturn(refreshTokenOptional);
    }

    @Test
    void refresh() {
    }

    @Test
    void delete() {
    }
}