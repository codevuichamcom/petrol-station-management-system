package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTO;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTOFilter;
import com.gasstation.managementsystem.service.PumShiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "PumpShift", description = "API for pumpShift")
@RequiredArgsConstructor
public class PumpShiftController {
    private final PumShiftService pumShiftService;

    @Operation(summary = "View All PumpShift")
    @GetMapping("/pump-shifts")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "createdDateFrom", required = false) Long createdDateFrom,
                                          @RequestParam(name = "createdDateTo", required = false) Long createdDateTo,
                                          @RequestParam(name = "closedTimeFrom", required = false) Long closedTimeFrom,
                                          @RequestParam(name = "closedTimeTo", required = false) Long closedTimeTo,
                                          @RequestParam(name = "shiftName", required = false) String shiftName,
                                          @RequestParam(name = "pumpName", required = false) String pumpName,
                                          @RequestParam(name = "stationIds", required = false) Integer[] stationIds,
                                          @RequestParam(name = "stationName", required = false) String stationName,
                                          @RequestParam(name = "statuses", required = false) String[] statuses) {
        PumpShiftDTOFilter filter = PumpShiftDTOFilter.builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .createdDateFrom(createdDateFrom)
                .createdDateTo(createdDateTo)
                .closedTimeFrom(closedTimeFrom)
                .closedTimeTo(closedTimeTo)
                .shiftName(shiftName)
                .pumpName(pumpName)
                .stationIds(stationIds)
                .stationName(stationName)
                .statuses(statuses).build();
        return pumShiftService.findAll(filter);
    }

    @Operation(summary = "Find PumpShift by id")
    @GetMapping("/pump-shifts/{id}")
    public PumpShiftDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return pumShiftService.findById(id);
    }

    @Operation(summary = "Update Pump shift by id")
    @PutMapping("/pump-shifts/{id}")
    public PumpShiftDTO update(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return pumShiftService.update(id);
    }

    @Operation(summary = "Update Pump shift by station id")
    @PutMapping("/pump-shifts")
    public HashMap<String, Object> updateAllByStationId(@RequestParam(name = "stationId") Integer stationId) throws CustomNotFoundException {
        return pumShiftService.updateAllByStationId(stationId);
    }
}
