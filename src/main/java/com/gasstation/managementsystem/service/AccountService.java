package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomForbiddenException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.account.AccountDTO;
import com.gasstation.managementsystem.model.dto.account.AccountDTOCreate;
import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface AccountService {
    HashMap<String, Object> findAll(Pageable pageable);

    HashMap<String, Object> findAll();

    AccountDTO findById(int id) throws CustomNotFoundException;

    AccountDTO create(AccountDTOCreate accountDTOCreate) throws CustomDuplicateFieldException;

    AccountDTO update(int id, AccountDTOUpdate accountDTOUpdate) throws CustomDuplicateFieldException, CustomNotFoundException, CustomForbiddenException, CustomBadRequestException;

    AccountDTO delete(int id) throws CustomNotFoundException;

    AccountDTO findByUsername(String username);
}
