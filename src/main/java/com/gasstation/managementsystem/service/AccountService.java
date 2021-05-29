package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.model.dto.AccountDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface AccountService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public AccountDTO findById(int id);

    public AccountDTO save(Account account);

    public AccountDTO delete(int id);

    public AccountDTO findByUsername(String username);
}
