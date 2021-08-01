package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.model.dto.dashboard.DashboardDTOFilter;
import com.gasstation.managementsystem.repository.criteria.DashboardRepositoryCriteria;
import com.gasstation.managementsystem.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final DashboardRepositoryCriteria dashboardCriteria;

    @Override
    public HashMap<String, Object> statistic(DashboardDTOFilter filter) {
        return dashboardCriteria.statistic(filter);
    }

}
