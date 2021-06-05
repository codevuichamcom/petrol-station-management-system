package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.model.dto.StationDTO;
import com.gasstation.managementsystem.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Station", description = "API for station")

public class StationController {
    @Autowired
    StationService stationService;

    @Operation(summary = "View All Station")
    @GetMapping("/stations")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                          Principal principal) {
        if (pageSize != null) {
            return stationService.findAll(PageRequest.of(pageIndex - 1, pageSize), principal);
        }
        return stationService.findAll(principal);
    }

    @Operation(summary = "Find Station by id")
    @GetMapping("/stations/{id}")
    public StationDTO getOne(@PathVariable(name = "id") Integer id) {
        return stationService.findById(id);
    }

    @Operation(summary = "Create new Station")
    @PostMapping("/stations")
    public StationDTO create(@Valid @RequestBody Station station) {
        return stationService.save(station);
    }

    @Operation(summary = "Update Station by id")
    @PutMapping("/stations/{id}")
    public StationDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Station station) {
        station.setId(id);
        return stationService.save(station);
    }

    @Operation(summary = "Delete Station by id")
    @DeleteMapping("/stations/{id}")
    public StationDTO delete(@PathVariable(name = "id") Integer id) {
        return stationService.delete(id);
    }
}
