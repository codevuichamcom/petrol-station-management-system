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
    public HashMap<String, Object> summary(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                           @RequestParam(name = "cardId", required = false) String cardId,
                                           @RequestParam(name = "stationIds", required = false) Integer[] stationIds,
                                           @RequestParam(name = "customerName", required = false) String customerName,
                                           @RequestParam(name = "customerPhone", required = false) String customerPhone,
                                           @RequestParam(name = "totalMoney", required = false) Double totalMoney) {
        DebtDTOSummaryFilter filter = DebtDTOSummaryFilter.builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .cardId(cardId)
                .stationIds(stationIds)
                .customerName(customerName)
                .customerPhone(customerPhone)
                .totalMoney(totalMoney).build();
        return debtService.summary(filter);

    }

    @Operation(summary = "Find debt by id")
    @GetMapping("/debts/{id}")
    public DebtDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return debtService.findById(id);
    }
}
