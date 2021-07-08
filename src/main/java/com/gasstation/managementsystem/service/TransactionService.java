package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface TransactionService {
    HashMap<String, Object> findAll(Pageable pageable);

    HashMap<String, Object> findAll();

    TransactionDTO findById(int id) throws CustomNotFoundException;

    TransactionDTO create(TransactionDTOCreate transactionDTOCreate) throws CustomNotFoundException;

    TransactionDTO update(int id, TransactionDTOUpdate transactionDTOUpdate) throws CustomNotFoundException;

    TransactionDTO delete(int id) throws CustomNotFoundException;
}
