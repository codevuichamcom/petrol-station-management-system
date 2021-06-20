package com.gasstation.managementsystem.model.dto.tank;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TankDTOCreate {
    private double volume;
    private double remain;
    private int stationId;
    private int fuelCategoryId;
}
