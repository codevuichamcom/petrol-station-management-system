package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOCreate;
import com.gasstation.managementsystem.service.HandOverShiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public HashMap<String, Object> getAll() {
        return handOverShiftService.findAll();
    }

    @Operation(summary = "Find HandOverShift by id")
    @GetMapping("/hand-over-shifts/{id}")
    public HandOverShiftDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return handOverShiftService.findById(id);
    }

    @Operation(summary = "Create new HandOverShift")
    @PostMapping("/hand-over-shifts")
    public HandOverShiftDTO create(@Valid @RequestBody HandOverShiftDTOCreate handOverShiftDTOCreate) throws CustomNotFoundException {
        return handOverShiftService.create(handOverShiftDTOCreate);
    }
}
