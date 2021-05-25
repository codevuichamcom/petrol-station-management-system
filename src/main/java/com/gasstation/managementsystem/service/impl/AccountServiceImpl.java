package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.model.dto.AccountDTO;
import com.gasstation.managementsystem.repository.AccountRepository;
import com.gasstation.managementsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder bcryptEncoder;

    @Override
    public List<AccountDTO> findAll() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account : accounts) {
            accountDTOS.add(new AccountDTO(account));
        }
        return accountDTOS;
    }

    @Override
    public AccountDTO findById(int id) {
        Account account = accountRepository.findById(id).get();
        return new AccountDTO(account);
    }

    public AccountDTO save(Account account) {
        account.setPassword(bcryptEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return new AccountDTO(account);
    }

    @Override
    public AccountDTO delete(int id) {
        Account account = accountRepository.findById(id).get();
        if (account != null) {
            accountRepository.delete(account);
            return new AccountDTO(account);
        }
        return null;
    }

    @Override
    public AccountDTO findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            return new AccountDTO(account);
        }
        return null;
    }
}
