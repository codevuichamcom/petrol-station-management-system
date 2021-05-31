package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.AccountDTO;
import com.gasstation.managementsystem.model.dto.UserDTO;
import com.gasstation.managementsystem.model.dto.UserTypeDTO;
import com.gasstation.managementsystem.repository.AccountRepository;
import com.gasstation.managementsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder bcryptEncoder;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Account> accounts = accountRepository.findAll(pageable);
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account : accounts) {
            AccountDTO accountDTO = new AccountDTO(account);
            User user = account.getUserInfo();
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .userType(new UserTypeDTO(user.getUserType())).build();
            accountDTO.setUserInfo(userDTO);
            accountDTOS.add(accountDTO);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", accountDTOS);
        map.put("totalElement", accounts.getTotalElements());
        map.put("totalPage", accounts.getTotalPages());
        return map;
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
