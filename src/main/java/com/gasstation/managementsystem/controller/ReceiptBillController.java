package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.ReceiptBill;
import com.gasstation.managementsystem.model.dto.ReceiptBillDTO;
import com.gasstation.managementsystem.service.ReceiptBillService;
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
@Tag(name = "ReceiptBill", description = "API for ReceiptBill")
public class ReceiptBillController {
    @Autowired
    ReceiptBillService ReceiptBillService;

    @Operation(summary = "View All ReceiptBill")
    @GetMapping("/receipt-bills")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return ReceiptBillService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find ReceiptBill by id")
    @GetMapping("/receipt-bills/{id}")
    public ReceiptBillDTO getOne(@PathVariable(name = "id") Integer id) {
        return ReceiptBillService.findById(id);
    }

    @Operation(summary = "Create new ReceiptBill")
    @PostMapping("/rreceipt-bills")
    public ReceiptBillDTO create(@Valid @RequestBody ReceiptBill receiptBill) {
        return ReceiptBillService.save(receiptBill);
    }

    @Operation(summary = "Update ReceiptBill by id")
    @PutMapping("/receipt-bills/{id}")
    public ReceiptBillDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody ReceiptBill receiptBill) {
        receiptBill.setId(id);
        return ReceiptBillService.save(receiptBill);
    }

    @Operation(summary = "Delete ReceiptBill by id")
    @DeleteMapping("/receipt-bills/{id}")
    public ReceiptBillDTO delete(@PathVariable(name = "id") Integer id) {
        return ReceiptBillService.delete(id);
    }
}
