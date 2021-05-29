package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.FuelImportBill;
import com.gasstation.managementsystem.model.dto.FuelImportBillDTO;
import com.gasstation.managementsystem.service.FuelImportBillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Fuel Import Bill", description = "API for Fuel Import Bill")
public class FuelImportBillController {

    @Autowired
    FuelImportBillService fuelImportBillService;

    @Operation(summary = "View All import bill")
    @GetMapping("/fuel-import-bills")
    public HashMap<String,Object> getAll(@RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,
                                         @RequestParam(name = "pageSize",defaultValue = "2")Integer pageSize) {
        return fuelImportBillService.findAll(PageRequest.of(pageIndex-1,pageSize));
    }

    @Operation(summary = "Find import bill by id")
    @GetMapping("/fuel-import-bills/{id}")
    public FuelImportBillDTO getOne(@PathVariable(name = "id") Integer id) {
        return fuelImportBillService.findById(id);
    }

    @Operation(summary = "Create new import bill")
    @PostMapping("/fuel-import-bills")
    public FuelImportBillDTO create(@Valid @RequestBody FuelImportBill fuelImportBill) {
        return fuelImportBillService.save(fuelImportBill);
    }

    @Operation(summary = "Update import bill by id")
    @PutMapping("/fuel-import-bills/{id}")
    public FuelImportBillDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody FuelImportBill fuelImportBill) {
        fuelImportBill.setId(id);
        return fuelImportBillService.save(fuelImportBill);
    }

    @Operation(summary = "Delete import bill by id")
    @DeleteMapping("/fuel-import-bills/{id}")
    public FuelImportBillDTO delete(@PathVariable(name = "id") Integer id) {
        return fuelImportBillService.delete(id);
    }
}
