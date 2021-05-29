package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import com.gasstation.managementsystem.model.dto.PriceChangeHistoryDTO;
import com.gasstation.managementsystem.service.PriceChangeHistoryService;
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
@Tag(name = "PriceChangeHistory", description = "API for PriceChangeHistory")
public class PriceChangeHistoryController {
    @Autowired
    PriceChangeHistoryService priceChangeHistoryService;

    @Operation(summary = "View All PriceChangeHistory")
    @GetMapping("/PriceChangeHistory")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return priceChangeHistoryService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find priceChangeHistory by id")
    @GetMapping("/PriceChangeHistory/{id}")
    public PriceChangeHistoryDTO getOne(@PathVariable(name = "id") Integer id) {
        return priceChangeHistoryService.findById(id);
    }

    @Operation(summary = "Create new priceChangeHistory")
    @PostMapping("/PriceChangeHistory")
    public PriceChangeHistoryDTO create(@Valid @RequestBody PriceChangeHistory priceChangeHistory) {
        return priceChangeHistoryService.save(priceChangeHistory);
    }

    @Operation(summary = "Update priceChangeHistory by id")
    @PutMapping("/PriceChangeHistory/{id}")
    public PriceChangeHistoryDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody PriceChangeHistory priceChangeHistory) {
        priceChangeHistory.setId(id);
        return priceChangeHistoryService.save(priceChangeHistory);
    }

    @Operation(summary = "Delete priceChangeHistory by id")
    @DeleteMapping("/PriceChangeHistory/{id}")
    public PriceChangeHistoryDTO delete(@PathVariable(name = "id") Integer id) {
        return priceChangeHistoryService.delete(id);
    }
}
