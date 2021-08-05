package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.model.dto.dashboard.FuelStatisticDTOFilter;
import com.gasstation.managementsystem.model.dto.dashboard.TankStatisticDTOFilter;

import java.util.HashMap;

public interface DashboardService {

    HashMap<String, Object> fuelStatistic(FuelStatisticDTOFilter filter);

    HashMap<String, Object> tankStatistic(TankStatisticDTOFilter filter);
}
