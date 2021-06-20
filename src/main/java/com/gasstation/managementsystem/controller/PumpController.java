package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.model.dto.PumpDTO;
import com.gasstation.managementsystem.service.PumpService;
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
@Tag(name = "Pump", description = "API for pump")
public class PumpController {
    @Autowired
    PumpService pumpService;

    @Operation(summary = "View All pump")
    @GetMapping("/pumps")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return pumpService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find pump by id")
    @GetMapping("/pumps/{id}")
    public PumpDTO getOne(@PathVariable(name = "id") Integer id) {
        return pumpService.findById(id);
    }

    @Operation(summary = "Create new pump")
    @PostMapping("/pumps")
    public PumpDTO create(@Valid @RequestBody Pump pump) {
        return pumpService.save(pump);
    }

    @Operation(summary = "Update pump by id")
    @PutMapping("/pumps/{id}")
    public PumpDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Pump pump) {
        pump.setId(id);
        return pumpService.save(pump);
    }

    @Operation(summary = "Delete pump by id")
    @DeleteMapping("/pumps/{id}")
    public PumpDTO delete(@PathVariable(name = "id") Integer id) {
        return pumpService.delete(id);
    }
}
