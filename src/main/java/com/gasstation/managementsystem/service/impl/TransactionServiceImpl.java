package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Transaction;
import com.gasstation.managementsystem.exception.custom.CustomInternalServerException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.mapper.TransactionMapper;
import com.gasstation.managementsystem.repository.TransactionRepository;
import com.gasstation.managementsystem.service.TransactionService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listTransactionToMap(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionDTOS.add(TransactionMapper.toTransactionDTO(transaction));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", transactionDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<com.gasstation.managementsystem.entity.Transaction> pumpCodes = transactionRepository.findAll(pageable);
        HashMap<String, Object> map = listTransactionToMap(pumpCodes.getContent());
        map.put("totalElement", pumpCodes.getTotalElements());
        map.put("totalPage", pumpCodes.getTotalPages());
        return map;
    }

    @Override
    public List<TransactionDTO> create(List<TransactionDTOCreate> transactionDTOCreates) throws CustomNotFoundException, CustomInternalServerException {
        List<Transaction> transactionList = new ArrayList<>();
        for (TransactionDTOCreate T : transactionDTOCreates) {
            Transaction transaction = TransactionMapper.toTransaction(T);
            Integer cardId = T.getCardId();
            if (cardId != null) {
                transaction.setCard(optionalValidate.getCardById(cardId));
            }
            transaction.setHandOverShift(optionalValidate.getHandOverShiftByPumpIdNotClose(T.getPumpId()));
            transactionList.add(transaction);
        }
        try {
            transactionList = transactionRepository.saveAll(transactionList);
        } catch (Exception ex) {
            throw new CustomInternalServerException(CustomError.builder().code("save.fail").message("Save List Transaction fail").build());
        }
        return transactionList.stream().map(TransactionMapper::toTransactionDTO).collect(Collectors.toList());
    }
}
