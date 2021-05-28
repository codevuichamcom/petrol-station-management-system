package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.model.dto.FuelDTO;
import com.gasstation.managementsystem.service.FuelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Fuel", description = "API for Fuel")
public class FuelController {

    @Autowired
    FuelService fuelService;

    @Operation(summary = "View All fuel category")
    @GetMapping("/fuels")
    public List<FuelDTO> getAll() {
        return fuelService.findAll();
    }

    @Operation(summary = "Find fuel category by id")
    @GetMapping("/fuels/{id}")
    public FuelDTO getOne(@PathVariable(name = "id") Integer id) {
        return fuelService.findById(id);
    }

    @Operation(summary = "Create new fuel category")
    @PostMapping("/fuels")
    public FuelDTO create(@Valid @RequestBody Fuel fuel) {
        return fuelService.save(fuel);
    }

    @Operation(summary = "Update fuel category by id")
    @PutMapping("/fuels/{id}")
    public FuelDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Fuel fuel) {
        fuel.setId(id);
        return fuelService.save(fuel);
    }

    @Operation(summary = "Delete fuel category by id")
    @DeleteMapping("/fuels/{id}")
    public FuelDTO delete(@PathVariable(name = "id") Integer id) {
        return fuelService.delete(id);
    }
}
