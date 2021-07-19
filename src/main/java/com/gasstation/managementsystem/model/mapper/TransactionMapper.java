package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;

public class TransactionMapper {
    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        if (transaction == null) return null;
        Card card = transaction.getCard();
        HandOverShift handOverShift = transaction.getHandOverShift();
        CardDTO cardDTO = card != null ? CardDTO.builder().id(card.getId())
                .customer(UserDTO.builder()
                        .id(card.getCustomer().getId())
                        .name(card.getCustomer().getName())
                        .build()).build() : null;
        Shift shift = handOverShift.getShift();
        Pump pump = handOverShift.getPump();
        Tank tank = pump.getTank();
        Station station = tank.getStation();
        TankDTO tankDTO = tank != null ? TankDTO.builder()
                .id(tank.getId())
                .name(tank.getName())
                .station(StationDTO.builder()
                        .id(station.getId())
                        .name(station.getName())
                        .address(station.getAddress())
                        .build()).build() : null;
        HandOverShiftDTO handOverShiftDTO = HandOverShiftDTO.builder()
                .id(handOverShift.getId())
                .shift(ShiftDTO.builder().id(shift.getId()).name(shift.getName()).build())
                .pump(PumpDTO.builder().id(pump.getId()).name(pump.getName()).tank(tankDTO).build())
                .build();
        return TransactionDTO.builder()
                .id(transaction.getId())
                .time(DateTimeHelper.formatDate(transaction.getTime(), "yyyy-MM-dd HH:mm:ss"))
                .volume(transaction.getVolume())
                .unitPrice(transaction.getUnitPrice())
                .uuid(transaction.getUuid())
                .card(cardDTO)
                .handOverShift(handOverShiftDTO)
                .build();
    }

    public static Transaction toTransaction(TransactionDTOCreate transactionDTOCreate) {
        if (transactionDTOCreate == null) return null;
        return Transaction.builder()
                .time(transactionDTOCreate.getTime())
                .volume(transactionDTOCreate.getVolume() / 1000)
                .unitPrice(transactionDTOCreate.getUnitPrice())
                .uuid(transactionDTOCreate.getUuid())
                .build();
    }
}
