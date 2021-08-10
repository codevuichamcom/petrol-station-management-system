package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;

public class TransactionMapper {
    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        if (transaction == null) return null;
        Card card = transaction.getCard();
        PumpShift pumpShift = transaction.getPumpShift();
        CardDTO cardDTO = card != null ? CardDTO.builder().id(card.getId())
                .customer(UserDTO.builder()
                        .id(card.getCustomer().getId())
                        .name(card.getCustomer().getName())
                        .build()).build() : null;
        Shift shift = pumpShift.getShift();
        Pump pump = pumpShift.getPump();
        Tank tank = pump.getTank();
        Station station = tank.getStation();
        TankDTO tankDTO = TankDTO.builder()
                .id(tank.getId())
                .name(tank.getName())
                .station(StationDTO.builder()
                        .id(station.getId())
                        .name(station.getName())
                        .address(station.getAddress())
                        .build()).build();
        PumpShiftDTO pumpShiftDTO = PumpShiftDTO.builder()
                .id(pumpShift.getId())
                .shift(ShiftDTO.builder().id(shift.getId()).name(shift.getName()).build())
                .pump(PumpDTO.builder().id(pump.getId()).name(pump.getName()).tank(tankDTO).build())
                .build();
        return TransactionDTO.builder()
                .id(transaction.getId())
                .time(transaction.getTime())
                .volume(transaction.getVolume())
                .unitPrice(transaction.getUnitPrice())
                .totalAmount(transaction.getTotalAmount())
                .uuid(transaction.getUuid())
                .card(cardDTO)
                .pumpShift(pumpShiftDTO)
                .build();
    }

    public static Transaction toTransaction(TransactionDTOCreate transactionDTOCreate) {
        if (transactionDTOCreate == null) return null;
        return Transaction.builder()
                .time(transactionDTOCreate.getTime())
                .volume(transactionDTOCreate.getVolume() / 1000)
                .unitPrice(transactionDTOCreate.getUnitPrice())
                .uuid(transactionDTOCreate.getUuid())
                .totalAmount(transactionDTOCreate.getTotalAmount())
                .build();
    }
}
