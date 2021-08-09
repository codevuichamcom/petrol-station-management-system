package com.gasstation.managementsystem.model.dto.debt;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DebtDTOFilter {
    private UUID cardId;
    private Integer stationId;
}
