package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.model.dto.account.AccountDTO;
import com.gasstation.managementsystem.model.dto.account.AccountDTOCreate;
import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;
import com.gasstation.managementsystem.model.mapper.AccountMapper;
import com.gasstation.managementsystem.repository.AccountRepository;
import com.gasstation.managementsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder bcryptEncoder;
    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Account> accounts = accountRepository.findAll(pageable);
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account : accounts) {
            accountDTOS.add(AccountMapper.toAccountDTO(account));
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
        return AccountMapper.toAccountDTO(account);
    }

    public AccountDTO create(AccountDTOCreate accountDTOCreate) {
        Account account = AccountMapper.toAccount(accountDTOCreate);
        account.setPassword(bcryptEncoder.encode(accountDTOCreate.getPassword()));
        accountRepository.save(account);
        return AccountMapper.toAccountDTO(account);
    }

    @Override
    public AccountDTO update(int id,AccountDTOUpdate accountDTOUpdate) {
        Account account = AccountMapper.toAccount(accountDTOUpdate);
        account.setId(id);
        account.setPassword(bcryptEncoder.encode(accountDTOUpdate.getPassword()));
        accountRepository.save(account);
        return AccountMapper.toAccountDTO(account);
    }


    @Override
    public AccountDTO delete(int id) {
        Account account = accountRepository.findById(id).get();
        if (account != null) {
            accountRepository.delete(account);
            return AccountMapper.toAccountDTO(account);
        }
        return null;
    }

    @Override
    public AccountDTO findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            return AccountMapper.toAccountDTO(account);
        }
        return null;
    }
}
