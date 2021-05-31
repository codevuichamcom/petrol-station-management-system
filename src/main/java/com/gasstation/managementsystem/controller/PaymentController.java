package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Payment;
import com.gasstation.managementsystem.model.dto.PaymentDTO;
import com.gasstation.managementsystem.service.PaymentService;
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
@Tag(name = "Payment", description = "API for Payment")

public class PaymentController {
    @Autowired
    PaymentService PaymentService;

    @Operation(summary = "View All Payment")
    @GetMapping("/payments")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return PaymentService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find Payment by id")
    @GetMapping("/payments/{id}")
    public PaymentDTO getOne(@PathVariable(name = "id") Integer id) {
        return PaymentService.findById(id);
    }

    @Operation(summary = "Create new Payment")
    @PostMapping("/payments")
    public PaymentDTO create(@Valid @RequestBody Payment payment) {
        return PaymentService.save(payment);
    }

    @Operation(summary = "Update Payment by id")
    @PutMapping("/payments/{id}")
    public PaymentDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Payment payment) {
        payment.setId(id);
        return PaymentService.save(payment);
    }

    @Operation(summary = "Delete Payment by id")
    @DeleteMapping("/payments/{id}")
    public PaymentDTO delete(@PathVariable(name = "id") Integer id) {
        return PaymentService.delete(id);
    }
}
