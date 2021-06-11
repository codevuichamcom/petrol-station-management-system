package com.gasstation.managementsystem.utils;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.repository.AccountRepository;
import com.gasstation.managementsystem.repository.UserRepository;
import com.gasstation.managementsystem.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class OptionalValidate {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final AccountRepository accountRepository;

    public Account getAccountById(int id) throws CustomNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (!accountOptional.isPresent()) {
            throw new CustomNotFoundException("Account is not found", "user", "user_table");
        }
        return accountOptional.get();
    }
    public User getUserById(int id) throws CustomNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new CustomNotFoundException("User is not found", "user", "user_table");
        }
        return userOptional.get();
    }

    public UserType getUserTypeById(Integer userTypeId) throws CustomBadRequestException {
        Optional<UserType> userTypeOptional = userTypeRepository.findById(userTypeId);
        if (userTypeOptional.isPresent()) {
            return userTypeOptional.get();
        } else {
            throw new CustomBadRequestException("User Type is not exist", "userTypeId", "user_type_table");
        }
    }
}
