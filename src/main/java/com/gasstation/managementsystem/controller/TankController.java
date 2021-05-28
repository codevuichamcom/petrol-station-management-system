package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.TankDTO;
import com.gasstation.managementsystem.service.TankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Tank", description = "API for Tank")
public class TankController {

    @Autowired
    TankService tankService;

    @Operation(summary = "View All tank")
    @GetMapping("/tanks")
    public List<TankDTO> getAll() {
        return tankService.findAll();
    }

    @Operation(summary = "Find tank by id")
    @GetMapping("/tanks/{id}")
    public TankDTO getOne(@PathVariable(name = "id") Integer id) {
        return tankService.findById(id);
    }

    @Operation(summary = "Create new tank")
    @PostMapping("/tanks")
    public TankDTO create(@Valid @RequestBody Tank tank) {
        return tankService.save(tank);
    }

    @Operation(summary = "Update tank by id")
    @PutMapping("/tanks/{id}")
    public TankDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Tank tank) {
        tank.setId(id);
        return tankService.save(tank);
    }

    @Operation(summary = "Delete tank by id")
    @DeleteMapping("/tanks/{id}")
    public TankDTO delete(@PathVariable(name = "id") Integer id) {
        return tankService.delete(id);
    }
}
