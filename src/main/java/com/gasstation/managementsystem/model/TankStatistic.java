package com.gasstation.managementsystem.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TankStatistic {
    private Integer tankId;
    private String tankName;
    private Integer stationId;
    private String stationName;
    private Double tankRemain;
    private Integer fuelId;
    private String fuelName;
    private Double totalImport;
    private Double totalExport;
}
