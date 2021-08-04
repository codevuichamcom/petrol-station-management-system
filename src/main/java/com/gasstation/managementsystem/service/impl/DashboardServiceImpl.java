package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.model.Dashboard;
import com.gasstation.managementsystem.model.dto.dashboard.DashboardDTOFilter;
import com.gasstation.managementsystem.model.mapper.DashboardMapper;
import com.gasstation.managementsystem.repository.criteria.DashboardRepositoryCriteria;
import com.gasstation.managementsystem.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final DashboardRepositoryCriteria dashboardCriteria;

    @Override
    public HashMap<String, Object> statistic(DashboardDTOFilter filter) {
        HashMap<String, Object> temp = dashboardCriteria.statistic(filter);
        HashMap<String, Object> map = new HashMap<>();
        List<Dashboard> dashboardList = (List<Dashboard>) temp.get("data");
        map.put("data", dashboardList.stream().map(DashboardMapper::toDashboardDTO).collect(Collectors.toList()));
        return map;
    }

}
