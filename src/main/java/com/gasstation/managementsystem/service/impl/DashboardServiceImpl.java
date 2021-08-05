package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.model.FuelStatistic;
import com.gasstation.managementsystem.model.TankStatistic;
import com.gasstation.managementsystem.model.dto.dashboard.FuelStatisticDTOFilter;
import com.gasstation.managementsystem.model.dto.dashboard.TankStatisticDTOFilter;
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
    public HashMap<String, Object> fuelStatistic(FuelStatisticDTOFilter filter) {
        HashMap<String, Object> temp = dashboardCriteria.fuelStatistic(filter);
        HashMap<String, Object> map = new HashMap<>();
        List<FuelStatistic> fuelStatisticList = (List<FuelStatistic>) temp.get("data");
        map.put("data", fuelStatisticList.stream().map(DashboardMapper::toFuelStatisticDTO).collect(Collectors.toList()));
        return map;
    }

    @Override
    public HashMap<String, Object> tankStatistic(TankStatisticDTOFilter filter) {
        HashMap<String, Object> temp = dashboardCriteria.tankStatistic(filter);
        HashMap<String, Object> map = new HashMap<>();
        List<TankStatistic> tankStatisticList = (List<TankStatistic>) temp.get("data");
        map.put("data", tankStatisticList.stream().map(DashboardMapper::toTankStatisticDTO).collect(Collectors.toList()));
        return map;
    }

}
