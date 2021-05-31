package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.model.dto.ShiftDTO;
import com.gasstation.managementsystem.service.ShiftService;
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
@Tag(name = "Shift", description = "API for shift")
public class ShiftController {
    @Autowired
    ShiftService ShiftService;

    @Operation(summary = "View All Shift")
    @GetMapping("/shifts")
    public HashMap<String,Object> getAll(@RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,
                                         @RequestParam(name = "pageSize",defaultValue = "2")Integer pageSize) {
        return ShiftService.findAll(PageRequest.of(pageIndex-1,pageSize));
    }

    @Operation(summary = "Find Shift by id")
    @GetMapping("/shifts/{id}")
    public ShiftDTO getOne(@PathVariable(name = "id") Integer id) {
        return ShiftService.findById(id);
    }

    @Operation(summary = "Create new Shift")
    @PostMapping("/shifts")
    public ShiftDTO create(@Valid @RequestBody Shift shift) {
        return ShiftService.save(shift);
    }

    @Operation(summary = "Update Shift by id")
    @PutMapping("/shifts/{id}")
    public ShiftDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Shift shift) {
        shift.setId(id);
        return ShiftService.save(shift);
    }

    @Operation(summary = "Delete Shift by id")
    @DeleteMapping("/shifts/{id}")
    public ShiftDTO delete(@PathVariable(name = "id") Integer id) {
        return ShiftService.delete(id);
    }
}
