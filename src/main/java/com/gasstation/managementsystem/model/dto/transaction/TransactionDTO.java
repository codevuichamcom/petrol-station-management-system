package com.gasstation.managementsystem.model.dto.transaction;

import com.gasstation.managementsystem.model.dto.card.CardDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionDTO {
    private int id;
    private Long time;
    private Double volume;
    private Double unitPrice;
    private CardDTO card;

}
