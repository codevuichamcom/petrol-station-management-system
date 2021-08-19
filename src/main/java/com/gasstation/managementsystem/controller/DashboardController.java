package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.model.dto.dashboard.FuelStatisticDTOFilter;
import com.gasstation.managementsystem.model.dto.dashboard.TankStatisticDTOFilter;
import com.gasstation.managementsystem.service.DashboardService;
import com.gasstation.managementsystem.service.StationService;
import com.gasstation.managementsystem.utils.UserHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = Api.PREFIX)
@CrossOrigin
@Tag(name = "Dashboard", description = "API for dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;
    private final UserHelper userHelper;
    private final StationService stationService;

    @Operation(summary = "Fuel statistic")
    @GetMapping("/fuel-statistic")
    public HashMap<String, Object> fuelStatistic(@RequestParam(name = "startTime") Long startTime,
                                                 @RequestParam(name = "endTime") Long endTime,
                                                 @RequestParam(name = "stationIds", required = false) Integer[] stationIds) {
        FuelStatisticDTOFilter filter = FuelStatisticDTOFilter.builder()
                .startTime(startTime)
                .endTime(endTime).stationIds(stationIds).build();

        return dashboardService.fuelStatistic(filter);
    }

    @Operation(summary = "Tank statistic")
    @GetMapping("/tank-statistic")
    public HashMap<String, Object> tankStatistic(@RequestParam(name = "startTime") Long startTime,
                                                 @RequestParam(name = "endTime") Long endTime,
                                                 @RequestParam(name = "stationIds", required = false) Integer[] stationIds) {
        TankStatisticDTOFilter filter = TankStatisticDTOFilter.builder()
                .startTime(startTime)
                .endTime(endTime)
                .stationIds(stationIds).build();
        return dashboardService.tankStatistic(filter);
    }
}
