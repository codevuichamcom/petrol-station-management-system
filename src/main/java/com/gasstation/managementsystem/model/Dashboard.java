package com.gasstation.managementsystem.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Dashboard {
    private Integer fuelId;
    private String fuelName;
    private Double revenue;
}
