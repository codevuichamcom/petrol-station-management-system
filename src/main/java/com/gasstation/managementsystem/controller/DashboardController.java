package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.model.dto.dashboard.DashboardDTOFilter;
import com.gasstation.managementsystem.service.DashboardService;
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

    @Operation(summary = "Statistics")
    @GetMapping("/dashboard")
    public HashMap<String, Object> getAll(@RequestParam(name = "startTime") Long startTime,
                                          @RequestParam(name = "endTime") Long endTime,
                                          @RequestParam(name = "stationId", required = false) Integer stationId) {
        DashboardDTOFilter filter = DashboardDTOFilter.builder()
                .startTime(startTime)
                .endTime(endTime).stationId(stationId).build();
        return dashboardService.statistic(filter);
    }
}
