package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DebtMapper {
    public static DebtDTO toDebtDTO(Debt debt) {
        Card card = debt.getCard();
        User customer = card != null ? card.getCustomer() : null;
        UserDTO customerDTO = customer != null ? UserDTO.builder().id(customer.getId()).name(customer.getName()).build() : null;
        CardDTO cardDTO = card != null ? CardDTO.builder().id(card.getId()).customer(customerDTO).build() : null;
        Station station = debt.getStation();
        StationDTO stationDTO = station != null ? StationDTO.builder()
                .id(station.getId())
                .name(station.getName())
                .address(station.getAddress())
                .build() : null;
        List<Transaction> transactionList = debt.getCard().getTransactionList();
        List<TransactionDTO> transactionDTOList = transactionList.stream().map(transaction -> TransactionDTO.builder()
                .id(transaction.getId())
                .time(transaction.getTime())
                .unitPrice(transaction.getUnitPrice())
                .volume(transaction.getVolume()).build()).collect(Collectors.toList());
        return DebtDTO.builder()
                .id(debt.getId())
                .amount(debt.getAmount())
                .card(cardDTO)
                .station(stationDTO)
                .transactions(transactionDTOList).build();
    }
}
