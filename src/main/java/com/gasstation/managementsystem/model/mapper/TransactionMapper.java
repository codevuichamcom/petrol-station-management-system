package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.model.dto.transaction.Transaction;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOUpdate;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class TransactionMapper {
    public static Transaction toPumpCodeDTO(com.gasstation.managementsystem.entity.Transaction transaction) {
        return Transaction.builder()
                .id(transaction.getId())
                .time(DateTimeHelper.toUnixTime(transaction.getTime()))
                .volume(transaction.getVolume())
                .unitPrice(transaction.getUnitPrice()).build();
    }

    public static com.gasstation.managementsystem.entity.Transaction toPumpCode(TransactionDTOCreate transactionDTOCreate) {
        return com.gasstation.managementsystem.entity.Transaction.builder()
                .time(DateTimeHelper.toDate(transactionDTOCreate.getTime()))
                .volume(transactionDTOCreate.getVolume())
                .unitPrice(transactionDTOCreate.getUnitPrice()).build();
    }

    public static void copyNonNullToFuel(com.gasstation.managementsystem.entity.Transaction transaction, TransactionDTOUpdate transactionDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(transaction, transactionDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
