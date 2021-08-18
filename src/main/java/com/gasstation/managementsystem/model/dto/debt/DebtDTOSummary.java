package com.gasstation.managementsystem.model.dto.debt;

import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DebtDTOSummary {
    private CardDTO card;
    private StationDTO station;
    private Double totalAccountsPayable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebtDTOSummary that = (DebtDTOSummary) o;
        return Objects.equals(card, that.card) && Objects.equals(station, that.station) && Objects.equals(totalAccountsPayable, that.totalAccountsPayable);
    }
}
