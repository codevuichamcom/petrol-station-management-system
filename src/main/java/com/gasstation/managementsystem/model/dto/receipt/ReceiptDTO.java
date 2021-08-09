package com.gasstation.managementsystem.model.dto.receipt;

import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReceiptDTO {
    private int id;
    private Long createdDate;
    private String reason;
    private Double amount;
    private UserDTO creator;
    private CardDTO card;
    private TransactionDTO transaction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptDTO that = (ReceiptDTO) o;
        return id == that.id && Objects.equals(createdDate, that.createdDate) && Objects.equals(reason, that.reason) && Objects.equals(amount, that.amount) && Objects.equals(creator, that.creator) && Objects.equals(card, that.card) && Objects.equals(transaction, that.transaction);
    }
}
