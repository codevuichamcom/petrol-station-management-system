package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Supplier;
import com.gasstation.managementsystem.model.dto.SupplierDTO;
import com.gasstation.managementsystem.service.SupplierService;
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
@Tag(name = "Supplier", description = "API for supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @Operation(summary = "View All supplier")
    @GetMapping("/suppliers")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return supplierService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find supplier by id")
    @GetMapping("/suppliers/{id}")
    public SupplierDTO getOne(@PathVariable(name = "id") Integer id) {
        return supplierService.findById(id);
    }

    @Operation(summary = "Create new supplier")
    @PostMapping("/suppliers")
    public SupplierDTO create(@Valid @RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @Operation(summary = "Update supplier by id")
    @PutMapping("/suppliers/{id}")
    public SupplierDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Supplier supplier) {
        supplier.setId(id);
        return supplierService.save(supplier);
    }

    @Operation(summary = "Delete supplier by id")
    @DeleteMapping("/suppliers/{id}")
    public SupplierDTO delete(@PathVariable(name = "id") Integer id) {
        return supplierService.delete(id);
    }
}
