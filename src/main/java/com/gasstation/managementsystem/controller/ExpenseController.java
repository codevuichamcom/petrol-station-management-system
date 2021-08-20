package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTO;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOCreate;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOFilter;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOUpdate;
import com.gasstation.managementsystem.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Expense", description = "API for expense")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @Operation(summary = "View All expense")
    @GetMapping("/expenses")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "amountFrom", required = false) Double amountFrom,
                                          @RequestParam(name = "amountTo", required = false) Double amountTo,
                                          @RequestParam(name = "createdDateFrom", required = false) Long createdDateFrom,
                                          @RequestParam(name = "createdDateTo", required = false) Long createdDateTo,
                                          @RequestParam(name = "stationName", required = false) String stationName,
                                          @RequestParam(name = "reason", required = false) String reason,
                                          @RequestParam(name = "creatorName", required = false) String creatorName) {
        ExpenseDTOFilter filter = ExpenseDTOFilter.builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .amountFrom(amountFrom)
                .amountTo(amountTo)
                .createdDateFrom(createdDateFrom)
                .createdDateTo(createdDateTo)
                .stationName(stationName)
                .creatorName(creatorName)
                .reason(reason)
                .build();
        return expenseService.findAll(filter);

    }

    @Operation(summary = "Find expense by id")
    @GetMapping("/expenses/{id}")
    public ExpenseDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return expenseService.findById(id);
    }

    @Operation(summary = "Create new expense")
    @PostMapping("/expenses")
    public ExpenseDTO create(@Valid @RequestBody ExpenseDTOCreate expenseDTOCreate) throws CustomNotFoundException {
        return expenseService.create(expenseDTOCreate);
    }

    @Operation(summary = "Update expense by id")
    @PutMapping("/expenses/{id}")
    public ExpenseDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody ExpenseDTOUpdate expenseDTOUpdate) throws CustomNotFoundException {
        return expenseService.update(id, expenseDTOUpdate);
    }

    @Operation(summary = "Delete expense by id")
    @DeleteMapping("/expenses/{id}")
    public ExpenseDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return expenseService.delete(id);
    }
}
