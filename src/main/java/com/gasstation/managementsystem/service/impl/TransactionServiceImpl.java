package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.transaction.Transaction;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOUpdate;
import com.gasstation.managementsystem.model.mapper.TransactionMapper;
import com.gasstation.managementsystem.repository.TransactionRepository;
import com.gasstation.managementsystem.service.TransactionService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listPumpCodeToMap(List<com.gasstation.managementsystem.entity.Transaction> transactions) {
        List<Transaction> pumpCodeDTOS = new ArrayList<>();
        for (com.gasstation.managementsystem.entity.Transaction transaction : transactions) {
            pumpCodeDTOS.add(TransactionMapper.toTransactionDTO(transaction));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", pumpCodeDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<com.gasstation.managementsystem.entity.Transaction> pumpCodes = transactionRepository.findAll(pageable);
        HashMap<String, Object> map = listPumpCodeToMap(pumpCodes.getContent());
        map.put("totalElement", pumpCodes.getTotalElements());
        map.put("totalPage", pumpCodes.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listPumpCodeToMap(transactionRepository.findAll());
    }

    @Override
    public Transaction findById(int id) throws CustomNotFoundException {
        return TransactionMapper.toTransactionDTO(optionalValidate.getPumpCodeById(id));
    }

    @Override
    public Transaction create(TransactionDTOCreate transactionDTOCreate) throws CustomNotFoundException {
        com.gasstation.managementsystem.entity.Transaction transaction = TransactionMapper.toTransaction(transactionDTOCreate);
        Card card = optionalValidate.getCardById(transactionDTOCreate.getCardId());
        transaction.setCard(card);
        transaction = transactionRepository.save(transaction);
        return TransactionMapper.toTransactionDTO(transaction);
    }

    @Override
    public Transaction update(int id, TransactionDTOUpdate transactionDTOUpdate) throws CustomNotFoundException {
        com.gasstation.managementsystem.entity.Transaction transaction = optionalValidate.getPumpCodeById(id);
        TransactionMapper.copyNonNullToTransaction(transaction, transactionDTOUpdate);
        Integer cardId = transactionDTOUpdate.getCardId();
        if (cardId != null) {
            transaction.setCard(optionalValidate.getCardById(cardId));
        }
        transaction = transactionRepository.save(transaction);
        return TransactionMapper.toTransactionDTO(transaction);
    }


    @Override
    public Transaction delete(int id) throws CustomNotFoundException {
        com.gasstation.managementsystem.entity.Transaction transaction = optionalValidate.getPumpCodeById(id);
        transactionRepository.delete(transaction);
        return TransactionMapper.toTransactionDTO(transaction);
    }
}
