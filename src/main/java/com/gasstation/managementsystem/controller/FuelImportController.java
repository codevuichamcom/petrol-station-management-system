package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOCreate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOUpdate;
import com.gasstation.managementsystem.service.FuelImportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Fuel Import", description = "API for Fuel Import")
@RequiredArgsConstructor
public class FuelImportController {

    private final FuelImportService fuelImportService;

    @Operation(summary = "View All import bill")
    @GetMapping("/fuel-imports")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return fuelImportService.findAll();
    }

    @Operation(summary = "Find import bill by id")
    @GetMapping("/fuel-imports/{id}")
    public FuelImportDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return fuelImportService.findById(id);
    }

    @Operation(summary = "Create new import bill")
    @PostMapping("/fuel-imports")
    public FuelImportDTO create(@Valid @RequestBody FuelImportDTOCreate fuelImportDTOCreate) throws CustomNotFoundException, CustomBadRequestException {
        return fuelImportService.create(fuelImportDTOCreate);
    }

    @Operation(summary = "Update import bill by id")
    @PutMapping("/fuel-imports/{id}")
    public FuelImportDTO update(@PathVariable(name = "id") Integer id,
                                @Valid @RequestBody FuelImportDTOUpdate fuelImportDTOUpdate) throws CustomNotFoundException, CustomBadRequestException {
        return fuelImportService.update(id, fuelImportDTOUpdate);
    }

    @Operation(summary = "Delete import bill by id")
    @DeleteMapping("/fuel-imports/{id}")
    public FuelImportDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException, CustomBadRequestException {
        return fuelImportService.delete(id);
    }
}
