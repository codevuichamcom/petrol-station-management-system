package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.transaction.Transaction;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOUpdate;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface TransactionService {
    HashMap<String, Object> findAll(Pageable pageable);

    HashMap<String, Object> findAll();

    Transaction findById(int id) throws CustomNotFoundException;

    Transaction create(TransactionDTOCreate transactionDTOCreate) throws CustomNotFoundException;

    Transaction update(int id, TransactionDTOUpdate transactionDTOUpdate) throws CustomNotFoundException;

    Transaction delete(int id) throws CustomNotFoundException;
}
