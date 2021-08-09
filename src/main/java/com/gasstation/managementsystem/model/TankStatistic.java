package com.gasstation.managementsystem.model;

import com.gasstation.managementsystem.entity.Tank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TankStatistic {
    private Tank tank;
    private Double totalImport;
    private Double totalExport;
}
