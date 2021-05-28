package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.FuelCategory;
import com.gasstation.managementsystem.model.dto.FuelCategoryDTO;
import com.gasstation.managementsystem.service.FuelCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Fuel Category", description = "API for Fuel Category")
public class FuelCategoryController {

    @Autowired
    FuelCategoryService fuelCategoryService;

    @Operation(summary = "View All fuel category")
    @GetMapping("/fuel-categories")
    public List<FuelCategoryDTO> getAll() {
        return fuelCategoryService.findAll();
    }

    @Operation(summary = "Find fuel category by id")
    @GetMapping("/fuel-categories/{id}")
    public FuelCategoryDTO getOne(@PathVariable(name = "id") Integer id) {
        return fuelCategoryService.findById(id);
    }

    @Operation(summary = "Create new fuel category")
    @PostMapping("/fuel-categories")
    public FuelCategoryDTO create(@Valid @RequestBody FuelCategory fuelCategory) {
        return fuelCategoryService.save(fuelCategory);
    }

    @Operation(summary = "Update fuel category by id")
    @PutMapping("/fuel-categories/{id}")
    public FuelCategoryDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody FuelCategory fuelCategory) {
        fuelCategory.setId(id);
        return fuelCategoryService.save(fuelCategory);
    }

    @Operation(summary = "Delete fuel category by id")
    @DeleteMapping("/fuel-categories/{id}")
    public FuelCategoryDTO delete(@PathVariable(name = "id") Integer id) {
        return fuelCategoryService.delete(id);
    }
}
