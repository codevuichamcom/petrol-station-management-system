package com.gasstation.managementsystem.model.dto.receipt;

import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

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
    private DebtDTO debt;
}
