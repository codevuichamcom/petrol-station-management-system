package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOFilter;
import com.gasstation.managementsystem.service.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Receipt", description = "API for Receipt")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;

    @Operation(summary = "View All Receipt")
    @GetMapping("/receipts")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "createdDateFrom", required = false) Long createdDateFrom,
                                          @RequestParam(name = "createdDateTo", required = false) Long createdDateTo,
                                          @RequestParam(name = "amountFrom", required = false) Double amountFrom,
                                          @RequestParam(name = "amountTo", required = false) Double amountTo,
                                          @RequestParam(name = "customerName", required = false) String customerName,
                                          @RequestParam(name = "reason", required = false) String reason,
                                          @RequestParam(name = "creatorName", required = false) String creatorName) {
        ReceiptDTOFilter filter = ReceiptDTOFilter.builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .createdDateFrom(createdDateFrom)
                .createdDateTo(createdDateTo)
                .amountFrom(amountFrom)
                .amountTo(amountTo)
                .customerName(customerName)
                .creatorName(creatorName)
                .reason(reason).build();
        return receiptService.findAll(filter);
    }

    @Operation(summary = "Find Receipt by id")
    @GetMapping("/receipts/{id}")
    public ReceiptDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return receiptService.findById(id);
    }
}
