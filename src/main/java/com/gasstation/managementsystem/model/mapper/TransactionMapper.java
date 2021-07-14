package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.HandOverShift;
import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.entity.Transaction;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
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
        HandOverShiftDTO handOverShiftDTO = handOverShift != null ? HandOverShiftDTO.builder()
                .id(handOverShift.getId())
                .shift(ShiftDTO.builder().id(shift.getId()).name(shift.getName()).build())
                .build() : null;
        return TransactionDTO.builder()
                .id(transaction.getId())
                .time(DateTimeHelper.toUnixTime(transaction.getTime()))
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
                .time(DateTimeHelper.toDate(transactionDTOCreate.getTime()))
                .volume(transactionDTOCreate.getVolume())
                .unitPrice(transactionDTOCreate.getUnitPrice())
                .uuid(transactionDTOCreate.getUuid())
                .build();
    }
}
