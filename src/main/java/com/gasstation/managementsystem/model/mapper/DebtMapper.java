package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.Debt;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;

public class DebtMapper {
    public static DebtDTO toDebtDTO(Debt debt) {
        Card card = debt.getCard();
        CardDTO cardDTO = card != null ? CardDTO.builder().id(card.getId()).build() : null;
        Station station = debt.getStation();
        StationDTO stationDTO = station != null ? StationDTO.builder()
                .id(station.getId())
                .name(station.getName())
                .address(station.getAddress())
                .build() : null;
        return DebtDTO.builder()
                .id(debt.getId())
                .amount(debt.getAmount())
                .card(cardDTO)
                .station(stationDTO).build();
    }
}
