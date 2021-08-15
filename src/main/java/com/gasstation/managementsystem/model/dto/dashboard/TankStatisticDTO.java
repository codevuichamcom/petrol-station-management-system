package com.gasstation.managementsystem.model.dto.dashboard;

import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TankStatisticDTO {
    private TankDTO tank;
    private Double totalImport;
    private Double totalExport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TankStatisticDTO that = (TankStatisticDTO) o;
        return Objects.equals(tank, that.tank) && Objects.equals(totalImport, that.totalImport) && Objects.equals(totalExport, that.totalExport);
    }
}
