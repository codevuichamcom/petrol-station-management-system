package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;
import com.gasstation.managementsystem.service.ShiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Shift", description = "API for shift")
@RequiredArgsConstructor
public class ShiftController {
    private final ShiftService shiftService;

    @Operation(summary = "View All Shift")
    @GetMapping("/shifts")
    public HashMap<String, Object> getAll() {
        return shiftService.findAll();
    }

    @Operation(summary = "Find Shift by id")
    @GetMapping("/shifts/{id}")
    public ShiftDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return shiftService.findById(id);
    }

    @Operation(summary = "Create new Shift")
    @PostMapping("/shifts")
    public ShiftDTO create(@Valid @RequestBody ShiftDTOCreate shiftDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return shiftService.create(shiftDTOCreate);
    }

    @Operation(summary = "Update Shift by id")
    @PutMapping("/shifts/{id}")
    public ShiftDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody ShiftDTOUpdate shiftDTOUpdate) throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException {
        return shiftService.update(id, shiftDTOUpdate);
    }

    @Operation(summary = "Delete Shift by id")
    @DeleteMapping("/shifts/{id}")
    public ShiftDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException, CustomBadRequestException {
        return shiftService.delete(id);
    }
}
