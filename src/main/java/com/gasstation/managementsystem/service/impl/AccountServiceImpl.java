package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.account.AccountDTO;
import com.gasstation.managementsystem.model.dto.account.AccountDTOCreate;
import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;
import com.gasstation.managementsystem.model.mapper.AccountMapper;
import com.gasstation.managementsystem.repository.AccountRepository;
import com.gasstation.managementsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder bcryptEncoder;


    private HashMap<String, Object> listAccountToMap(List<Account> accounts) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account : accounts) {
            accountDTOS.add(AccountMapper.toAccountDTO(account));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", accountDTOS);
        return map;
    }

    private Account getAccountById(int id) throws CustomNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (!accountOptional.isPresent()) {
            throw new CustomNotFoundException("Account is not found", "user", "user_table");
        }
        return accountOptional.get();
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Account> accounts = accountRepository.findAll(pageable);
        HashMap<String, Object> map = listAccountToMap(accounts.getContent());
        map.put("totalElement", accounts.getTotalElements());
        map.put("totalPage", accounts.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listAccountToMap(accountRepository.findAll());
    }

    @Override
    public AccountDTO findById(int id) throws CustomNotFoundException {
        Account account = getAccountById(id);
        return AccountMapper.toAccountDTO(account);
    }

    public AccountDTO create(AccountDTOCreate accountDTOCreate) throws CustomDuplicateFieldException {
        Account account = AccountMapper.toAccount(accountDTOCreate);
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new CustomDuplicateFieldException("Duplicate field '" + account.getUsername() + "'", "username", null);
        }
        account.setPassword(bcryptEncoder.encode(accountDTOCreate.getPassword()));
        account = accountRepository.save(account);
        return AccountMapper.toAccountDTO(account);
    }

    @Override
    public AccountDTO update(int id, AccountDTOUpdate accountDTOUpdate) throws CustomDuplicateFieldException, CustomNotFoundException {
        if (accountDTOUpdate.getUsername() != null) { //check duplicate username
            if (accountRepository.findByUsername(accountDTOUpdate.getUsername()) != null) {
                throw new CustomDuplicateFieldException("Duplicate field '" + accountDTOUpdate.getUsername() + "'", "username", null);
            }
        }

        Account account = getAccountById(id);
        AccountMapper.copyNonNullToAccount(account, accountDTOUpdate);
        account.setId(id);
        if (accountDTOUpdate.getPassword() != null) {
            account.setPassword(bcryptEncoder.encode(accountDTOUpdate.getPassword()));
        }
        account = accountRepository.save(account);
        return AccountMapper.toAccountDTO(account);
    }


    @Override
    public AccountDTO delete(int id) throws CustomNotFoundException {
        Account account = getAccountById(id);
        accountRepository.delete(account);
        return AccountMapper.toAccountDTO(account);
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
