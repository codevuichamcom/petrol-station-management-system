package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.model.Dashboard;
import com.gasstation.managementsystem.model.dto.dashboard.DashboardDTO;

public class DashboardMapper {

    public static DashboardDTO toDashboardDTO(Dashboard dashboard) {
        if (dashboard == null) return null;
        return DashboardDTO.builder()
                .fuelId(dashboard.getFuelId())
                .fuelName(dashboard.getFuelName())
                .revenue(dashboard.getRevenue()).build();
    }
}
