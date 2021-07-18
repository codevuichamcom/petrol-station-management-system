package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.service.PriceChangeHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Price Change History", description = "API for PriceChangeHistory")
@RequiredArgsConstructor
public class PriceChangeHistoryController {
    private final PriceChangeHistoryService priceChangeHistoryService;

    @Operation(summary = "View All PriceChangeHistory")
    @GetMapping("/price-change-histories")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                          @RequestParam(name = "tankId") Integer tankId) {
        if (pageSize != null) {
            return priceChangeHistoryService.findAllByTankId(tankId, PageRequest.of(pageIndex - 1, pageSize, Sort.by(Sort.Direction.ASC, "id")));
        }
        return priceChangeHistoryService.findAllByTankId(tankId);
    }

//    @Operation(summary = "Find priceChangeHistory by id")
//    @GetMapping("/price-change-history/{id}")
//    public PriceChangeHistoryDTO getOne(@PathVariable(name = "id") Integer id) {
//        return priceChangeHistoryService.findById(id);
//    }
//
//    @Operation(summary = "Create new priceChangeHistory")
//    @PostMapping("/price-change-history")
//    public PriceChangeHistoryDTO create(@Valid @RequestBody PriceChangeHistory priceChangeHistory) {
//        return priceChangeHistoryService.save(priceChangeHistory);
//    }
//
//    @Operation(summary = "Update priceChangeHistory by id")
//    @PutMapping("/price-change-history/{id}")
//    public PriceChangeHistoryDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody PriceChangeHistory priceChangeHistory) {
//        priceChangeHistory.setId(id);
//        return priceChangeHistoryService.save(priceChangeHistory);
//    }
//
//    @Operation(summary = "Delete priceChangeHistory by id")
//    @DeleteMapping("/price-change-history/{id}")
//    public PriceChangeHistoryDTO delete(@PathVariable(name = "id") Integer id) {
//        return priceChangeHistoryService.delete(id);
//    }
}
