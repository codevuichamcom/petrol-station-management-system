package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;

public class DebtMapper {
    public static DebtDTO toDebtDTO(Debt debt) {
        Card card = debt.getTransaction().getCard();
        User customer = card != null ? card.getCustomer() : null;
        UserDTO customerDTO = customer != null ? UserDTO.builder().id(customer.getId()).name(customer.getName()).build() : null;
        CardDTO cardDTO = card != null ? CardDTO.builder().id(card.getId()).customer(customerDTO).build() : null;
        Station station = debt.getTransaction().getHandOverShift().getShift().getStation();
        StationDTO stationDTO = station != null ? StationDTO.builder()
                .id(station.getId())
                .name(station.getName())
                .address(station.getAddress())
                .build() : null;
        Transaction transaction = debt.getTransaction();
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .id(transaction.getId())
                .time(transaction.getTime())
                .unitPrice(transaction.getUnitPrice())
                .volume(transaction.getVolume()).build();
        return DebtDTO.builder()
                .id(debt.getId())
                .accountsPayable(debt.getAccountsPayable())
                .card(cardDTO)
                .station(stationDTO)
                .transaction(transactionDTO).build();
    }
}
