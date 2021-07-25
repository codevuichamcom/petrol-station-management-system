package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.Receipt;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;

public class ReceiptMapper {
    public static ReceiptDTO toReceiptDTO(Receipt receipt) {
        if (receipt == null) return null;
        User creator = receipt.getCreator();
        UserDTO creatorDTO = creator != null ? UserDTO.builder().id(creator.getId()).name(creator.getName()).build() : null;
        Card card = receipt.getCard();
        CardDTO cardDTO = card != null ? CardDTO.builder().id(card.getId()).build() : null;
        return ReceiptDTO.builder()
                .id(receipt.getId())
                .date(receipt.getDate())
                .reason(receipt.getReason())
                .amount(receipt.getAmount())
                .discount(receipt.getDiscount())
                .note(receipt.getNote())
                .creator(creatorDTO)
                .card(cardDTO)
                .build();
    }

    public static Receipt toReceipt(ReceiptDTOCreate receiptDTOCreate) {
        if (receiptDTOCreate == null) return null;
        return Receipt.builder()
                .date(receiptDTOCreate.getDate())
                .reason(receiptDTOCreate.getReason())
                .amount(receiptDTOCreate.getAmount())
                .discount(receiptDTOCreate.getDiscount())
                .note(receiptDTOCreate.getNote())
                .build();
    }
}
