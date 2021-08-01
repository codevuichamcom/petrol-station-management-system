package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;
import com.gasstation.managementsystem.service.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public HashMap<String, Object> getAll() {
        return receiptService.findAll();
    }

    @Operation(summary = "Find Receipt by id")
    @GetMapping("/receipts/{id}")
    public ReceiptDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return receiptService.findById(id);
    }

    @Operation(summary = "Create new Receipt")
    @PostMapping("/receipts")
    public ReceiptDTO create(@Valid @RequestBody ReceiptDTOCreate receiptDTOCreate) throws CustomNotFoundException {
        return receiptService.create(receiptDTOCreate);
    }
}
