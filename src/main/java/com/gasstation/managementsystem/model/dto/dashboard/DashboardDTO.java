package com.gasstation.managementsystem.model.dto.dashboard;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DashboardDTO {
    private Integer fuelId;
    private String fuelName;
    private Double revenue;
}
