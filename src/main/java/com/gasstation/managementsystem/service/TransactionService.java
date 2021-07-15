package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionUuidDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface TransactionService {
    HashMap<String, Object> findAll(Pageable pageable);

    List<TransactionUuidDTO> create(List<TransactionDTOCreate> transactionDTOCreates) throws CustomNotFoundException;

}
