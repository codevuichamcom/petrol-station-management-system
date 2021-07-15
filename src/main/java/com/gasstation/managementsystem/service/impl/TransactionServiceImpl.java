package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Transaction;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionUuidDTO;
import com.gasstation.managementsystem.model.mapper.TransactionMapper;
import com.gasstation.managementsystem.repository.TransactionRepository;
import com.gasstation.managementsystem.service.TransactionService;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
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
    public List<TransactionUuidDTO> create(List<TransactionDTOCreate> transactionDTOCreates) throws CustomNotFoundException {
        List<Transaction> transactionList = new ArrayList<>();
        for (TransactionDTOCreate T : transactionDTOCreates) {
            Transaction transaction = TransactionMapper.toTransaction(T);
            Integer cardId = T.getCardId();
            if (cardId != null) {
                transaction.setCard(optionalValidate.getCardById(cardId));
            }
            LocalDateTime localDateTime = DateTimeHelper.toDateTime(T.getTime());
            String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
            long seconds = localDateTime.getHour() * 3600 + localDateTime.getMinute() * 60 + localDateTime.getSecond();
            transaction.setHandOverShift(optionalValidate.getHandOverShiftByPumpIdNotClose(T.getPumpId(), Date.valueOf(date), seconds));
            transactionList.add(transaction);
        }

        List<TransactionUuidDTO> listUuidSync = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            try {
                transactionRepository.save(transaction);
                listUuidSync.add(TransactionUuidDTO.builder().uuid(transaction.getUuid()).build());
            } catch (DataIntegrityViolationException ex) {
                listUuidSync.add(TransactionUuidDTO.builder().uuid(transaction.getUuid()).build());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return listUuidSync;
    }
}
