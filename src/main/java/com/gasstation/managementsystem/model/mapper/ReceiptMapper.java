package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;

public class ReceiptMapper {
    public static ReceiptDTO toReceiptDTO(Receipt receipt) {
        if (receipt == null) return null;
        User creator = receipt.getCreator();
        UserDTO creatorDTO = creator != null ? UserDTO.builder().id(creator.getId()).name(creator.getName()).build() : null;
        Card card = receipt.getCard();
        User customer = card != null ? card.getCustomer() : null;
        UserDTO customerDTO = customer != null ? UserDTO.builder().id(customer.getId()).name(customer.getName()).phone(customer.getPhone()).build() : null;
        CardDTO cardDTO = card != null ? CardDTO.builder().id(card.getId()).customer(customerDTO).build() : null;
        Debt debt = receipt.getDebt();
        Transaction transaction = debt != null ? debt.getTransaction() : null;
        HandOverShift handOverShift = transaction != null ? transaction.getHandOverShift() : null;
        Pump pump = handOverShift != null ? handOverShift.getPump() : null;
        Tank tank = pump != null ? pump.getTank() : null;
        Station station = null;
        Fuel fuel = null;
        if (tank != null) {
            station = tank.getStation();
            fuel = tank.getFuel();
        }
        StationDTO stationDTO = station != null ? StationDTO.builder()
                .id(station.getId())
                .name(station.getName())
                .address(station.getAddress()).build() : null;
        FuelDTO fuelDTO = fuel != null ? FuelDTO.builder()
                .id(fuel.getId())
                .name(fuel.getName()).build() : null;
        TankDTO tankDTO = tank != null ? TankDTO.builder()
                .station(stationDTO)
                .fuel(fuelDTO).build() : null;
        PumpDTO pumpDTO = pump != null ? PumpDTO.builder()
                .tank(tankDTO).build() : null;
        HandOverShiftDTO handOverShiftDTO = handOverShift != null ? HandOverShiftDTO.builder()
                .pump(pumpDTO).build() : null;
        TransactionDTO transactionDTO = transaction != null ? TransactionDTO.builder()
                .id(transaction.getId())
                .time(transaction.getTime())
                .handOverShift(handOverShiftDTO).build() : null;
        DebtDTO debtDTO = debt != null ? DebtDTO.builder()
                .id(debt.getId())
                .accountsPayable(debt.getAccountsPayable())
                .transaction(transactionDTO).build() : null;

        return ReceiptDTO.builder()
                .id(receipt.getId())
                .createdDate(receipt.getCreatedDate())
                .reason(receipt.getReason())
                .amount(receipt.getAmount())
                .creator(creatorDTO)
                .card(cardDTO)
                .debt(debtDTO)
                .build();
    }

    public static Receipt toReceipt(ReceiptDTOCreate receiptDTOCreate) {
        if (receiptDTOCreate == null) return null;
        return Receipt.builder()
                .createdDate(DateTimeHelper.getCurrentDate())
                .reason(receiptDTOCreate.getReason())
                .amount(receiptDTOCreate.getAmount())
                .build();
    }
}
