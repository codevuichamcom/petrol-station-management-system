package com.gasstation.managementsystem.model.dto.tank;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TankDTOUpdate {
    private Double volume;
    private Double remain;
    private Integer stationId;
    private Integer fuelCategoryId;
}
