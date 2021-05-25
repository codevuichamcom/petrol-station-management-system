package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.model.dto.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    public List<AccountDTO> findAll();

    public AccountDTO findById(int id);

    public AccountDTO save(Account account);

    public AccountDTO delete(int id);

    public AccountDTO findByUsername(String username);
}
