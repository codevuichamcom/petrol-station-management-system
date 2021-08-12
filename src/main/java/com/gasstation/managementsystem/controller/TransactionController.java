package com.gasstation.managementsystem.controller;


import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOFilter;
import com.gasstation.managementsystem.model.dto.transaction.TransactionUuidDTO;
import com.gasstation.managementsystem.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Transaction", description = "API for Transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "View All Transaction")
    @GetMapping("/transactions")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "pumpIds", required = false) Integer[] pumpIds,
                                          @RequestParam(name = "shiftIds", required = false) Integer[] shiftIds,
                                          @RequestParam(name = "stationIds", required = false) Integer[] stationIds,
                                          @RequestParam(name = "timeFrom", required = false) Long timeFrom,
                                          @RequestParam(name = "timeTo", required = false) Long timeTo,
                                          @RequestParam(name = "unitPriceFrom", required = false) Double unitPriceFrom,
                                          @RequestParam(name = "unitPriceTo", required = false) Double unitPriceTo,
                                          @RequestParam(name = "volumeFrom", required = false) Double volumeFrom,
                                          @RequestParam(name = "volumeTo", required = false) Double volumeTo,
                                          @RequestParam(name = "totalAmount", required = false) Double totalAmount) {
        TransactionDTOFilter transactionDTOFilter = TransactionDTOFilter.builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .pumpIds(pumpIds)
                .shiftIds(shiftIds)
                .stationIds(stationIds)
                .timeFrom(timeFrom)
                .timeTo(timeTo)
                .unitPriceFrom(unitPriceFrom)
                .unitPriceTo(unitPriceTo)
                .volumeFrom(volumeFrom)
                .volumeTo(volumeTo).build();
        return transactionService.findAll(transactionDTOFilter);
    }


    @Operation(summary = "Create new Transaction")
    @PostMapping("/transactions")
    public HashMap<String, List<TransactionUuidDTO>> create(@Valid @RequestBody List<TransactionDTOCreate> transactionDTOCreates) throws CustomNotFoundException {
        HashMap<String, List<TransactionUuidDTO>> map = new HashMap<>();
        map.put("listUuidSync", transactionService.create(transactionDTOCreates));
        return map;
    }

}
