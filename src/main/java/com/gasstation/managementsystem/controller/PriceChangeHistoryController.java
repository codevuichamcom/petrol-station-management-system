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
}
