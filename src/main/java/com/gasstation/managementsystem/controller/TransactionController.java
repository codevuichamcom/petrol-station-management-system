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
                                          @RequestParam(name = "timeFrom", required = false) Long timeFrom,
                                          @RequestParam(name = "timeTo", required = false) Long timeTo,
                                          @RequestParam(name = "unitPriceFrom", required = false) Double unitPriceFrom,
                                          @RequestParam(name = "unitPriceTo", required = false) Double unitPriceTo,
                                          @RequestParam(name = "volumeFrom", required = false) Double volumeFrom,
                                          @RequestParam(name = "volumeTo", required = false) Double volumeTo,
                                          @RequestParam(name = "amountFrom", required = false) Double amountFrom,
                                          @RequestParam(name = "amountTo", required = false) Double amountTo,
                                          @RequestParam(name = "pumpName", required = false) String pumpName,
                                          @RequestParam(name = "shiftName", required = false) String shiftName,
                                          @RequestParam(name = "stationName", required = false) String stationName) {
        TransactionDTOFilter transactionDTOFilter = TransactionDTOFilter.builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .timeFrom(timeFrom)
                .timeTo(timeTo)
                .unitPriceFrom(unitPriceFrom)
                .unitPriceTo(unitPriceTo)
                .volumeFrom(volumeFrom)
                .volumeTo(volumeTo)
                .amountFrom(amountFrom)
                .amountTo(amountTo)
                .pumpName(pumpName)
                .shiftName(shiftName)
                .stationName(stationName).build();
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
