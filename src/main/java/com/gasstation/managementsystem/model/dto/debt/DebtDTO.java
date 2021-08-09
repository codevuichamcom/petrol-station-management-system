package com.gasstation.managementsystem.model.dto.debt;

import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DebtDTO {
    private int id;
    private CardDTO card;
    private StationDTO station;
    private TransactionDTO transaction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebtDTO debtDTO = (DebtDTO) o;
        return id == debtDTO.id && Objects.equals(card, debtDTO.card) && Objects.equals(station, debtDTO.station) && Objects.equals(transaction, debtDTO.transaction);
    }
}
