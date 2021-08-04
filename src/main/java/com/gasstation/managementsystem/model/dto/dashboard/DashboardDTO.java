package com.gasstation.managementsystem.model.dto.dashboard;

import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DashboardDTO {
    private FuelDTO fuel;
    private StationDTO station;
    private Double totalRevenue;
    private Double totalDebt;
    private Double totalMoney;
}
