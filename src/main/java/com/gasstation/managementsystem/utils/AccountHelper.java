package com.gasstation.managementsystem.utils;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountHelper {
    private final AccountRepository accountRepository;

    public Account getAccountLogined() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Account account = accountRepository.findByUsername(username);
            if (account != null) {
                return account;
            }
        }
        return null;
    }

    public UserType getUserTypeOfAccountLogined() {
        Account account = getAccountLogined();
        User userInfo = account.getUserInfo();
        return (userInfo != null) ? userInfo.getUserType() : null;
    }
}
