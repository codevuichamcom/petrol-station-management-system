package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.Transaction;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOUpdate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class TransactionMapper {
    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        if (transaction == null) return null;
        Card card = transaction.getCard();
        CardDTO cardDTO = card != null ? CardDTO.builder().id(card.getId())
                .customer(UserDTO.builder()
                        .id(card.getCustomer().getId())
                        .name(card.getCustomer().getName())
                        .build()).build() : null;
        return TransactionDTO.builder()
                .time(DateTimeHelper.toUnixTime(transaction.getTime()))
                .volume(transaction.getVolume())
                .unitPrice(transaction.getUnitPrice())
                .card(cardDTO)
                .build();
    }

    public static Transaction toTransaction(TransactionDTOCreate transactionDTOCreate) {
        if (transactionDTOCreate == null) return null;
        return Transaction.builder()
                .time(transactionDTOCreate.getTime())
                .volume(transactionDTOCreate.getVolume())
                .unitPrice(transactionDTOCreate.getUnitPrice()).build();
    }

    public static void copyNonNullToTransaction(Transaction transaction, TransactionDTOUpdate transactionDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(transaction, transactionDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
