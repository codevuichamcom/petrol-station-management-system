package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.PaymentBill;
import com.gasstation.managementsystem.model.dto.PaymentBillDTO;
import com.gasstation.managementsystem.service.PaymentBillService;
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
@Tag(name = "PaymentBill", description = "API for PaymentBill")
public class PaymentBillController {
    @Autowired
    PaymentBillService PaymentBillService;

    @Operation(summary = "View All PaymentBill")
    @GetMapping("/payment-bills")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return PaymentBillService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find PaymentBill by id")
    @GetMapping("/payment-bills/{id}")
    public PaymentBillDTO getOne(@PathVariable(name = "id") Integer id) {
        return PaymentBillService.findById(id);
    }

    @Operation(summary = "Create new PaymentBill")
    @PostMapping("/payment-bills")
    public PaymentBillDTO create(@Valid @RequestBody PaymentBill paymentBill) {
        return PaymentBillService.save(paymentBill);
    }

    @Operation(summary = "Update PaymentBill by id")
    @PutMapping("/payment-bills/{id}")
    public PaymentBillDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody PaymentBill paymentBill) {
        paymentBill.setId(id);
        return PaymentBillService.save(paymentBill);
    }

    @Operation(summary = "Delete PaymentBill by id")
    @DeleteMapping("/payment-bills/{id}")
    public PaymentBillDTO delete(@PathVariable(name = "id") Integer id) {
        return PaymentBillService.delete(id);
    }
}
