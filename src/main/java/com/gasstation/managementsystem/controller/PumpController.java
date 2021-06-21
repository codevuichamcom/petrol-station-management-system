package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOUpdate;
import com.gasstation.managementsystem.service.PumpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Pump", description = "API for pump")
@RequiredArgsConstructor
public class PumpController {
    private final PumpService pumpService;

    @Operation(summary = "View All pump")
    @GetMapping("/pumps")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (pageSize != null) {
            return pumpService.findAll(PageRequest.of(pageIndex - 1, pageSize));
        }
        return pumpService.findAll();

    }

    @Operation(summary = "Find pump by id")
    @GetMapping("/pumps/{id}")
    public PumpDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return pumpService.findById(id);
    }

    @Operation(summary = "Create new pump")
    @PostMapping("/pumps")
    public PumpDTO create(@Valid @RequestBody PumpDTOCreate pumpDTOCreate) {
        return pumpService.create(pumpDTOCreate);
    }

    @Operation(summary = "Update pump by id")
    @PutMapping("/pumps/{id}")
    public PumpDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody PumpDTOUpdate pumpDTOUpdate) throws CustomNotFoundException {
        return pumpService.update(id, pumpDTOUpdate);
    }

    @Operation(summary = "Delete pump by id")
    @DeleteMapping("/pumps/{id}")
    public PumpDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return pumpService.delete(id);
    }
}
