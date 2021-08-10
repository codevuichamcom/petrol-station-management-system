package com.gasstation.managementsystem.utils;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserHelperTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserHelper userHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * principal instanceof UserDetails
     */
    @Test
    void getUserLogin_UTCID01() {
        Long dateOfBirth = 16094340000000L;
        User mockResult = User.builder().id(1).username("user_" + 1)
                .password("123456")
                .username("user_1")
                .identityCardNumber("0136235943")
                .name("Le Viet Tu")
                .gender(false)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818939")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .active(false)
                .build();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(mockResult.getUsername(), mockResult.getPassword(), true, true, true, true, authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        Mockito.when(userRepository.findByUsername("user_1")).thenReturn(mockResult);
        assertEquals(mockResult, userHelper.getUserLogin());
    }

    /**
     * principal not instanceof UserDetails
     */
    @Test
    void getUserLogin_UTCID02() {
        Long dateOfBirth = 16094340000000L;
        User mockResult = User.builder().id(1).username("user_" + 1)
                .password("123456")
                .username("user_1")
                .identityCardNumber("0136235943")
                .name("Le Viet Tu")
                .gender(false)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818939")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .active(false)
                .build();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(mockResult.getUsername(), mockResult.getPassword(), true, true, true, true, authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                null, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        Mockito.when(userRepository.findByUsername("user_1")).thenReturn(mockResult);
        assertEquals(null, userHelper.getUserLogin());
    }

    @Test
    void getUserTypeOfUserLogin() {
        Long dateOfBirth = 16094340000000L;
        User mockResult = User.builder().id(1).username("user_" + 1)
                .userType(UserType.builder().id(1).type("ADMIN").build())
                .password("123456")
                .username("user_1")
                .identityCardNumber("0136235943")
                .name("Le Viet Tu")
                .gender(false)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818939")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .active(false)
                .build();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(mockResult.getUsername(), mockResult.getPassword(), true, true, true, true, authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        Mockito.when(userRepository.findByUsername("user_1")).thenReturn(mockResult);
        Mockito.when(userHelper.getUserLogin()).thenReturn(mockResult);
        assertEquals(mockResult.getUserType(), userHelper.getUserTypeOfUserLogin());
    }

    @Test
    void isAdmin() {
        Long dateOfBirth = 16094340000000L;
        User mockResult = User.builder().id(1).username("user_" + 1)
                .userType(UserType.builder().id(1).type("ADMIN").build())
                .password("123456")
                .username("user_1")
                .identityCardNumber("0136235943")
                .name("Le Viet Tu")
                .gender(false)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818939")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .active(false)
                .build();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(mockResult.getUsername(), mockResult.getPassword(), true, true, true, true, authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        Mockito.when(userRepository.findByUsername("user_1")).thenReturn(mockResult);
        Mockito.when(userHelper.getUserLogin()).thenReturn(mockResult);
        assertEquals(true, userHelper.isAdmin());
    }

    @Test
    void isOwner() {
        Long dateOfBirth = 16094340000000L;
        User mockResult = User.builder().id(1).username("user_" + 1)
                .userType(UserType.builder().id(2).type("OWNER").build())
                .password("123456")
                .username("user_1")
                .identityCardNumber("0136235943")
                .name("Le Viet Tu")
                .gender(false)
                .dateOfBirth(dateOfBirth)
                .address("Me Linh")
                .phone("0886818939")
                .email("motdemhuyhoang@gmail.com")
                .note("")
                .active(false)
                .build();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(mockResult.getUsername(), mockResult.getPassword(), true, true, true, true, authorities);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        Mockito.when(userRepository.findByUsername("user_1")).thenReturn(mockResult);
        Mockito.when(userHelper.getUserLogin()).thenReturn(mockResult);
        assertEquals(true, userHelper.isOwner());
    }
}