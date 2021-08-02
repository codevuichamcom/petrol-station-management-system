package com.gasstation.managementsystem.model.dto.debt;

import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DebtDTO {
    private int id;
    private Double amount;
    private CardDTO card;
    private StationDTO station;
    private List<TransactionDTO> transactions;
}
