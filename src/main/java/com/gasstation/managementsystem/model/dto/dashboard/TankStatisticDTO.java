package com.gasstation.managementsystem.model.dto.dashboard;

import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TankStatisticDTO {
    private TankDTO tank;
    private FuelDTO fuel;
    private Double totalImport;
    private Double totalExport;
}
