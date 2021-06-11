package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.account.AccountDTO;
import com.gasstation.managementsystem.model.dto.account.AccountDTOCreate;
import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface AccountService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public HashMap<String, Object> findAll();

    public AccountDTO findById(int id) throws CustomNotFoundException;

    public AccountDTO create(AccountDTOCreate accountDTOCreate) throws CustomDuplicateFieldException;

    public AccountDTO update(int id, AccountDTOUpdate accountDTOUpdate) throws CustomDuplicateFieldException, CustomNotFoundException;

    public AccountDTO delete(int id) throws CustomNotFoundException;

    public AccountDTO findByUsername(String username);
}
