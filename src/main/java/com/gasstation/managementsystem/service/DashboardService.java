package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.model.dto.dashboard.FuelStatisticDTOFilter;

import java.util.HashMap;

public interface DashboardService {

    HashMap<String, Object> fuelStatistic(FuelStatisticDTOFilter filter);
}
