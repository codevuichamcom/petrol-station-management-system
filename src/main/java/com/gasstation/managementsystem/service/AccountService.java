package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.model.dto.account.AccountDTO;
import com.gasstation.managementsystem.model.dto.account.AccountDTOCreate;
import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface AccountService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public AccountDTO findById(int id);

    public AccountDTO create(AccountDTOCreate accountDTOCreate);

    public AccountDTO update(int id, AccountDTOUpdate accountDTOUpdate);

    public AccountDTO delete(int id);

    public AccountDTO findByUsername(String username);
}
