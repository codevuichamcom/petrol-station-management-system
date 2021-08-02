package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOSummaryFilter;
import com.gasstation.managementsystem.service.DebtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Debt", description = "API for debt")
@RequiredArgsConstructor
public class DebtController {
    private final DebtService debtService;

    @Operation(summary = "View All debt")
    @GetMapping("/debts")
    public HashMap<String, Object> summary() {
        DebtDTOSummaryFilter filter = DebtDTOSummaryFilter.builder().build();
        return debtService.summary(filter);

    }

    @Operation(summary = "Find debt by id")
    @GetMapping("/debts/{id}")
    public DebtDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return debtService.findById(id);
    }
}
