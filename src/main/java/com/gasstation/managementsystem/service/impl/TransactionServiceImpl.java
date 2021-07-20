package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.HandOverShift;
import com.gasstation.managementsystem.entity.Transaction;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOFilter;
import com.gasstation.managementsystem.model.dto.transaction.TransactionUuidDTO;
import com.gasstation.managementsystem.model.mapper.TransactionMapper;
import com.gasstation.managementsystem.repository.HandOverShiftRepository;
import com.gasstation.managementsystem.repository.PumpRepository;
import com.gasstation.managementsystem.repository.ShiftRepository;
import com.gasstation.managementsystem.repository.TransactionRepository;
import com.gasstation.managementsystem.repository.criteria.HandOverShiftRepositoryCriteria;
import com.gasstation.managementsystem.repository.criteria.TransactionRepositoryCriteria;
import com.gasstation.managementsystem.service.TransactionService;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final OptionalValidate optionalValidate;
    private final TransactionRepositoryCriteria transactionCriteria;
    private final HandOverShiftRepositoryCriteria handOverShiftCriteria;
    private final PumpRepository pumpRepository;
    private final ShiftRepository shiftRepository;
    private final HandOverShiftRepository handOverShiftRepository;

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
    public HashMap<String, Object> findAll(TransactionDTOFilter transactionDTOFilter) {
        HashMap<String, Object> temp = transactionCriteria.findAll(transactionDTOFilter);
        HashMap<String, Object> map = listTransactionToMap((List<Transaction>) temp.get("data"));
        map.put("totalElement", temp.get("totalElement"));
        map.put("totalPage", temp.get("totalPage"));
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
            LocalDate localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
            long seconds = localDateTime.getHour() * 3600 + localDateTime.getMinute() * 60 + localDateTime.getSecond();

            HandOverShift handOverShift = optionalValidate.getHandOverShiftByPumpIdNotClose(T.getPumpId(),
                    LocalDateTime.of(localDate, LocalTime.MIN)
                            .atZone(TimeZone.getDefault()
                                    .toZoneId()).toEpochSecond() * 1000, seconds);
            if (handOverShift == null) {
                handOverShift = handOverShiftCriteria.getHandOverShiftToday();
                if (handOverShift == null) {
                    createHandOverShiftForAllPump();
                    handOverShift = optionalValidate.getHandOverShiftByPumpIdNotClose(T.getPumpId(),
                            LocalDateTime.of(localDate, LocalTime.MIN)
                                    .atZone(TimeZone.getDefault()
                                            .toZoneId()).toEpochSecond() * 1000, seconds);
                } else {
                    throw new CustomNotFoundException(CustomError.builder()
                            .code("not.found").field("id").message("Hand over shift is not exist").table("hand_over_shift_table").build());
                }
            }
            if (handOverShift != null) {
                transaction.setHandOverShift(handOverShift);
            }
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

    private void createHandOverShiftForAllPump() {
        ArrayList<HandOverShift> handOverShifts = new ArrayList<>();
        pumpRepository.findAll().forEach(pump -> shiftRepository.findAll().forEach(shift -> {
            HandOverShift handOverShift = HandOverShift.builder().
                    createdDate(DateTimeHelper.getCurrentDate())
                    .shift(shift)
                    .pump(pump).build();
            handOverShifts.add(handOverShift);
        }));
        handOverShiftRepository.saveAll(handOverShifts);
    }

}
