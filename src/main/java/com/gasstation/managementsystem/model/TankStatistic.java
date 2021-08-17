package com.gasstation.managementsystem.model;

import com.gasstation.managementsystem.entity.Tank;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TankStatistic {
    private Tank tank;
    private Double totalImport;
    private Double totalExport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TankStatistic that = (TankStatistic) o;
        return Objects.equals(tank.getId(), that.tank.getId()) && Objects.equals(totalImport, that.totalImport) && Objects.equals(totalExport, that.totalExport);
    }
}
