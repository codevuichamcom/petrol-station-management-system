package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;
import com.gasstation.managementsystem.service.ReceiptBillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "ReceiptBill", description = "API for ReceiptBill")
@RequiredArgsConstructor
public class ReceiptBillController {
    private final ReceiptBillService ReceiptBillService;

    @Operation(summary = "View All ReceiptBill")
    @GetMapping("/receipt-bills")
    public HashMap<String, Object> getAll() {
        return ReceiptBillService.findAll();
    }

    @Operation(summary = "Find ReceiptBill by id")
    @GetMapping("/receipt-bills/{id}")
    public ReceiptDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return ReceiptBillService.findById(id);
    }

    @Operation(summary = "Create new ReceiptBill")
    @PostMapping("/receipt-bills")
    public ReceiptDTO create(@Valid @RequestBody ReceiptDTOCreate receiptDTOCreate) throws CustomNotFoundException {
        return ReceiptBillService.create(receiptDTOCreate);
    }
}
