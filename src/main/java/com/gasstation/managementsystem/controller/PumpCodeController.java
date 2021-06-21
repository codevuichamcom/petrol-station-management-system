package com.gasstation.managementsystem.controller;


import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTO;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTOCreate;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTOUpdate;
import com.gasstation.managementsystem.service.PumpCodeService;
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
@Tag(name = "Pump Code", description = "API for PumpCodeCode")
@RequiredArgsConstructor
public class PumpCodeController {
    private final PumpCodeService PumpCodeService;

    @Operation(summary = "View All PumpCode")
    @GetMapping("/pump-codes")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return PumpCodeService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find PumpCode by id")
    @GetMapping("/pump-codes/{id}")
    public PumpCodeDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return PumpCodeService.findById(id);
    }

    @Operation(summary = "Create new PumpCode")
    @PostMapping("/pump-codes")
    public PumpCodeDTO create(@Valid @RequestBody PumpCodeDTOCreate pumpCodeDTOCreate) throws CustomNotFoundException {
        return PumpCodeService.create(pumpCodeDTOCreate);
    }

    @Operation(summary = "Update PumpCode by id")
    @PutMapping("/pump-codes/{id}")
    public PumpCodeDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody PumpCodeDTOUpdate pumpCodeDTOUpdate) throws CustomNotFoundException {
        return PumpCodeService.update(id, pumpCodeDTOUpdate);
    }

    @Operation(summary = "Delete PumpCode by id")
    @DeleteMapping("/pump-codes/{id}")
    public PumpCodeDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return PumpCodeService.delete(id);
    }
}
