package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTOCreate;
import com.gasstation.managementsystem.model.dto.tank.TankDTOUpdate;
import com.gasstation.managementsystem.service.TankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Tank", description = "API for Tank")
@RequiredArgsConstructor
public class TankController {

    private final TankService tankService;

    @Operation(summary = "View All tank")
    @GetMapping("/tanks")
    public HashMap<String, Object> getAll() {
        return tankService.findAll();
    }

    @Operation(summary = "Find tank by id")
    @GetMapping("/tanks/{id}")
    public TankDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return tankService.findById(id);
    }

    @Operation(summary = "Create new tank")
    @PostMapping("/tanks")
    public TankDTO create(@Valid @RequestBody TankDTOCreate tankDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return tankService.create(tankDTOCreate);
    }

    @Operation(summary = "Update tank by id")
    @PutMapping("/tanks/{id}")
    public TankDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody TankDTOUpdate tankDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return tankService.update(id, tankDTOUpdate);
    }

    @Operation(summary = "Delete tank by id")
    @DeleteMapping("/tanks/{id}")
    public TankDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException, CustomBadRequestException {
        return tankService.delete(id);
    }
}
