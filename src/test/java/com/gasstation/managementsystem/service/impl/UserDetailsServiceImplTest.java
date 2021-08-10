package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserDetailsServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param username is null
     */
    @Test
    void loadUserByUsername_UTCID01() {
        Long dateOfBirth = 16094340000000L;
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        User mockRepository = User.builder().id(1)
                .userType(userType)
                .username("user_1").dateOfBirth(dateOfBirth).gender(false).active(false)
                .build();
        Mockito.when(userRepository.findByUsername("user_1")).thenReturn(mockRepository);
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsServiceImpl.loadUserByUsername("user_2");
        });
    }

    /**
     * param username is not null
     */
    @Test
    void loadUserByUsername_UTCID02() {
        Long dateOfBirth = 16094340000000L;
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        User mockRepository = User.builder().id(1)
                .userType(userType)
                .username("user_1")
                .password("1234567")
                .dateOfBirth(dateOfBirth)
                .gender(false)
                .active(false)
                .build();
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockRepository);
        Mockito.when(userRepository.findByUsername("user_1")).thenReturn(mockRepository);

        assertEquals(mockRepository.getUsername(), userDetailsServiceImpl.loadUserByUsername("user_1").getUsername());
    }
}