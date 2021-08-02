package com.gasstation.managementsystem.model.dto.debt;

import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DebtDTO {
    private int id;
    private Double accountsPayable;
    private CardDTO card;
    private StationDTO station;
    private TransactionDTO transaction;
}
