package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;
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
        UserDTO customerDTO = customer != null ? UserDTO.builder().id(customer.getId()).name(customer.getName()).build() : null;
        CardDTO cardDTO = card != null ? CardDTO.builder().id(card.getId()).customer(customerDTO).build() : null;
        Debt debt = receipt.getDebt();
        Transaction transaction = debt != null ? debt.getTransaction() : null;
        TransactionDTO transactionDTO = TransactionMapper.toTransactionDTO(transaction);
        DebtDTO debtDTO = debt != null ? DebtDTO.builder()
                .id(debt.getId())
                .accountsPayable(debt.getAccountsPayable())
                .transaction(transactionDTO).build() : null;

        return ReceiptDTO.builder()
                .id(receipt.getId())
                .createdDate(receipt.getCreatedDate())
                .reason(receipt.getReason())
                .amount(receipt.getAmount())
                .discount(receipt.getDiscount())
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
                .discount(receiptDTOCreate.getDiscount())
                .build();
    }
}
