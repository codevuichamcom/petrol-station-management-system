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
import java.util.HashMap;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Station", description = "API for Station")

public class StationController {
    @Autowired
    StationService StationService;

    @Operation(summary = "View All Station")
    @GetMapping("/Stations")
    public HashMap<String,Object> getAll(@RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,
                                         @RequestParam(name = "pageSize",defaultValue = "2")Integer pageSize) {
        return StationService.findAll(PageRequest.of(pageIndex-1,pageSize));
    }

    @Operation(summary = "Find Station by id")
    @GetMapping("/Stations/{id}")
    public StationDTO getOne(@PathVariable(name = "id") Integer id) {
        return StationService.findById(id);
    }

    @Operation(summary = "Create new Station")
    @PostMapping("/Stations")
    public StationDTO create(@Valid @RequestBody Station station) {
        return StationService.save(station);
    }

    @Operation(summary = "Update Station by id")
    @PutMapping("/Stations/{id}")
    public StationDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Station station) {
        station.setId(id);
        return StationService.save(station);
    }

    @Operation(summary = "Delete Station by id")
    @DeleteMapping("/Stations/{id}")
    public StationDTO delete(@PathVariable(name = "id") Integer id) {
        return StationService.delete(id);
    }
}
