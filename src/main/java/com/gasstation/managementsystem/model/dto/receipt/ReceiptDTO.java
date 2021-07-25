package com.gasstation.managementsystem.model.dto.receipt;

import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReceiptDTO {
    private int id;
    private Long date;
    private String reason;
    private Double amount;
    private Double discount;
    private String note;
    private UserDTO creator;
    private CardDTO card;
}
