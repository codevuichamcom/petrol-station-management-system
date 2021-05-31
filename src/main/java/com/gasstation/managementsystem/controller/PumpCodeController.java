package com.gasstation.managementsystem.controller;


import com.gasstation.managementsystem.entity.PumpCode;
import com.gasstation.managementsystem.model.dto.PumpCodeDTO;
import com.gasstation.managementsystem.service.PumpCodeService;
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
@Tag(name = "Pump Code", description = "API for PumpCodeCode")

public class PumpCodeController {
    @Autowired
    PumpCodeService PumpCodeService;

    @Operation(summary = "View All PumpCode")
    @GetMapping("/pump-codes")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return PumpCodeService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find PumpCode by id")
    @GetMapping("/pump-codes/{id}")
    public PumpCodeDTO getOne(@PathVariable(name = "id") Integer id) {
        return PumpCodeService.findById(id);
    }

    @Operation(summary = "Create new PumpCode")
    @PostMapping("/pump-codes")
    public PumpCodeDTO create(@Valid @RequestBody PumpCode pumpCode) {
        return PumpCodeService.save(pumpCode);
    }

    @Operation(summary = "Update PumpCode by id")
    @PutMapping("/pump-codes/{id}")
    public PumpCodeDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody PumpCode pumpCode) {
        pumpCode.setId(id);
        return PumpCodeService.save(pumpCode);
    }

    @Operation(summary = "Delete PumpCode by id")
    @DeleteMapping("/pump-codes/{id}")
    public PumpCodeDTO delete(@PathVariable(name = "id") Integer id) {
        return PumpCodeService.delete(id);
    }
}
