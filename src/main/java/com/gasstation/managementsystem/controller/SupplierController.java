package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOCreate;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOUpdate;
import com.gasstation.managementsystem.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Supplier", description = "API for supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @Operation(summary = "View All supplier")
    @GetMapping("/suppliers")
    public HashMap<String, Object> getAll() {
        return supplierService.findAll();

    }

    @Operation(summary = "Find supplier by id")
    @GetMapping("/suppliers/{id}")
    public SupplierDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return supplierService.findById(id);
    }

    @Operation(summary = "Create new supplier")
    @PostMapping("/suppliers")
    public SupplierDTO create(@Valid @RequestBody SupplierDTOCreate supplierDTOCreate) throws CustomDuplicateFieldException {
        return supplierService.create(supplierDTOCreate);
    }

    @Operation(summary = "Update supplier by id")
    @PutMapping("/suppliers/{id}")
    public SupplierDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody SupplierDTOUpdate supplierDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return supplierService.update(id, supplierDTOUpdate);
    }

    @Operation(summary = "Delete supplier by id")
    @DeleteMapping("/suppliers/{id}")
    public SupplierDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException, CustomBadRequestException {
        return supplierService.delete(id);
    }
}
