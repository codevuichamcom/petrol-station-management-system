package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.model.dto.dashboard.DashboardDTOFilter;

import java.util.HashMap;

public interface DashboardService {

    HashMap<String, Object> statistic(DashboardDTOFilter filter);
}
