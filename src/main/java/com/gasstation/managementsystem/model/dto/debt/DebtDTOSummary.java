package com.gasstation.managementsystem.model.dto.debt;

import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DebtDTOSummary {
    private CardDTO card;
    private StationDTO station;
    private Double totalMoney;
}
