package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.Debt;
import com.gasstation.managementsystem.entity.PumpShift;
import com.gasstation.managementsystem.entity.Transaction;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOFilter;
import com.gasstation.managementsystem.model.dto.transaction.TransactionUuidDTO;
import com.gasstation.managementsystem.model.mapper.TransactionMapper;
import com.gasstation.managementsystem.repository.*;
import com.gasstation.managementsystem.repository.criteria.PumpShiftRepositoryCriteria;
import com.gasstation.managementsystem.repository.criteria.TransactionRepositoryCriteria;
import com.gasstation.managementsystem.service.TransactionService;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final OptionalValidate optionalValidate;
    private final TransactionRepositoryCriteria transactionCriteria;
    private final PumpShiftRepositoryCriteria pumpShiftCriteria;
    private final PumpRepository pumpRepository;
    private final ShiftRepository shiftRepository;
    private final PumpShiftRepository pumpShiftRepository;
    private final DebtRepository debtRepository;
    private final CardRepository cardRepository;

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
        map.put("totalVolume", temp.get("totalVolume"));
        map.put("totalAmount", temp.get("totalAmount"));
        return map;

    }

    @Override
    public List<TransactionUuidDTO> create(List<TransactionDTOCreate> transactionDTOCreates) throws CustomNotFoundException {
        List<Transaction> transactionList = new ArrayList<>();

        for (TransactionDTOCreate T : transactionDTOCreates) {
            Transaction transaction = TransactionMapper.toTransaction(T);
            UUID cardId = T.getCardId();
            if (cardId != null) {
                Card card = optionalValidate.getCardById(cardId);
                transaction.setCard(card);
            }
            LocalDateTime localDateTime = DateTimeHelper.toDateTime(T.getTime());
            LocalDate localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
            long milliSeconds = (localDateTime.getHour() * 3600 + localDateTime.getMinute() * 60 + localDateTime.getSecond()) * 1000;

            PumpShift pumpShift = optionalValidate.getPumpShiftByPumpIdNotClose(T.getPumpId(),
                    LocalDateTime.of(localDate, LocalTime.MIN)
                            .atZone(TimeZone.getDefault()
                                    .toZoneId()).toEpochSecond() * 1000, milliSeconds);
            if (pumpShift == null) {
                throw new CustomNotFoundException(CustomError.builder()
                        .code("not.found").field("id").message("Pump shift is not exist").table("pump_shift_table").build());
            }
            transaction.setPumpShift(pumpShift);
            transactionList.add(transaction);
        }

        List<TransactionUuidDTO> listUuidSync = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            try {
                transactionRepository.save(transaction);
                Card card = transaction.getCard();
                if (card != null) {
                    double totalMoneyTransaction = Math.ceil(transaction.getUnitPrice() * transaction.getVolume());
                    double accountsPayable = totalMoneyTransaction - card.getAvailableBalance();
                    if (accountsPayable <= 0) {
                        card.setAvailableBalance(card.getAvailableBalance() - totalMoneyTransaction);
                    } else {
                        card.setAvailableBalance(0d);
                        card.setAccountsPayable(card.getAccountsPayable() + accountsPayable);
                        Debt debt = Debt.builder()
                                .accountsPayable(accountsPayable)
                                .transaction(transaction).build();
                        debtRepository.save(debt);
                    }
                    cardRepository.save(card);
                }
                listUuidSync.add(TransactionUuidDTO.builder().uuid(transaction.getUuid()).build());
            } catch (DataIntegrityViolationException ex) {
                listUuidSync.add(TransactionUuidDTO.builder().uuid(transaction.getUuid()).build());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return listUuidSync;
    }

    @Scheduled(cron = "0 30 1 * * ?")
    private void createPumpShiftForAllPump() {
        ArrayList<PumpShift> pumpShifts = new ArrayList<>();
        pumpRepository.findAll().forEach(pump -> {
            int stationId = pump.getTank().getStation().getId();
            shiftRepository.findAllShiftByStationId(stationId).forEach(shift -> {
                PumpShift pumpShift = PumpShift.builder().
                        createdDate(DateTimeHelper.getCurrentDate())
                        .shift(shift)
                        .pump(pump).build();
                pumpShifts.add(pumpShift);
            });
        });
        pumpShiftRepository.saveAll(pumpShifts);
    }

}
