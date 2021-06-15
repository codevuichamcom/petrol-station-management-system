package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomForbiddenException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.account.AccountDTO;
import com.gasstation.managementsystem.model.dto.account.AccountDTOCreate;
import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;
import com.gasstation.managementsystem.model.mapper.AccountMapper;
import com.gasstation.managementsystem.repository.AccountRepository;
import com.gasstation.managementsystem.service.AcceptTokenService;
import com.gasstation.managementsystem.service.AccountService;
import com.gasstation.managementsystem.utils.AccountHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
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
    private final OptionalValidate optionalValidate;
    private final AccountHelper accountHelper;
    private final AcceptTokenService acceptTokenService;


    private HashMap<String, Object> listAccountToMap(List<Account> accounts) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account : accounts) {
            accountDTOS.add(AccountMapper.toAccountDTO(account));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", accountDTOS);
        return map;
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
        Account account = optionalValidate.getAccountById(id);
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
    public AccountDTO update(int id, AccountDTOUpdate accountDTOUpdate) throws CustomDuplicateFieldException, CustomNotFoundException, CustomForbiddenException, CustomBadRequestException {
        Account account = accountHelper.getAccountLogined();
        if (accountDTOUpdate.getOldPassword() != null) {
            if (account.getId() == id) {
                Account accountUpdate = optionalValidate.getAccountById(id);
                String oldPassword = accountUpdate.getPassword();
                if (!bcryptEncoder.matches(accountDTOUpdate.getOldPassword(), oldPassword)) {
                    throw new CustomBadRequestException("Old password is incorrect", null, null);
                } else {
                    accountUpdate.setPassword(bcryptEncoder.encode(accountDTOUpdate.getNewPassword()));
                    account = accountRepository.save(accountUpdate);
                }
            } else {
                throw new CustomForbiddenException("Account incorrect");
            }
        } else {
            UserType userType = account.getUserInfo() != null ? account.getUserInfo().getUserType() : null;
            if (userType != null && userType.getId() == UserType.ADMIN) {
                Account accountUpdate = optionalValidate.getAccountById(id);
                accountUpdate.setPassword(bcryptEncoder.encode(accountDTOUpdate.getNewPassword()));
                account = accountRepository.save(accountUpdate);
            }
        }
        acceptTokenService.deleteByAccountId(account.getId());
        return AccountMapper.toAccountDTO(account);
    }


    @Override
    public AccountDTO delete(int id) throws CustomNotFoundException {
        Account account = optionalValidate.getAccountById(id);
        accountRepository.delete(account);
        return AccountMapper.toAccountDTO(account);
    }

    @Override
    public AccountDTO findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        return AccountMapper.toAccountDTO(account);
    }

}
