package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(account.getRole()));
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), true, true, true, true, authorities);
            return userDetails;
        }
    }
}
