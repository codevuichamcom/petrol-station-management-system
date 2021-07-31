package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOFilter;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdateHandOverShift;
import com.gasstation.managementsystem.service.HandOverShiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "HandOverShift", description = "API for handOverShift")
@RequiredArgsConstructor
public class HandOverShiftController {
    private final HandOverShiftService handOverShiftService;

    @Operation(summary = "View All HandOverShift")
    @GetMapping("/hand-over-shifts")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "createdDate", required = false) Long createdDate,
                                          @RequestParam(name = "closedTime", required = false) Long closedTime,
                                          @RequestParam(name = "shiftIds", required = false) Integer[] shiftIds,
                                          @RequestParam(name = "pumpIds", required = false) Integer[] pumpIds,
                                          @RequestParam(name = "stationIds", required = false) Integer[] stationIds,
                                          @RequestParam(name = "actorName", required = false) String actorName,
                                          @RequestParam(name = "statuses", required = false) String[] statuses) {
        HandOverShiftDTOFilter filter = HandOverShiftDTOFilter.builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .createdDate(createdDate)
                .closedTime(closedTime)
                .shiftIds(shiftIds)
                .pumpIds(pumpIds)
                .stationIds(stationIds)
                .actorName(actorName)
                .statuses(statuses).build();
        return handOverShiftService.findAll(filter);
    }

    @Operation(summary = "Find HandOverShift by id")
    @GetMapping("/hand-over-shifts/{id}")
    public HandOverShiftDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return handOverShiftService.findById(id);
    }

    @Operation(summary = "Hand Over Shift")
    @PutMapping("/hand-over-shifts/{id}")
    public HandOverShiftDTO update(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return handOverShiftService.update(id);
    }

    @Operation(summary = "Hand Over Shift")
    @PutMapping("/hand-over-shifts")
    public void updateAllByStationId(@RequestBody StationDTOUpdateHandOverShift stationId) throws CustomNotFoundException {
        handOverShiftService.updateAllByStationId(stationId);
    }
}
