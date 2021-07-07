package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Expense;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTO;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOCreate;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOUpdate;
import com.gasstation.managementsystem.model.mapper.ExpenseMapper;
import com.gasstation.managementsystem.repository.ExpenseRepository;
import com.gasstation.managementsystem.service.ExpenseService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listExpenseToMap(List<Expense> expenses) {
        List<ExpenseDTO> expenseDTOS = expenses.stream().map(ExpenseMapper::toExpenseDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", expenseDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Expense> expenses = expenseRepository.findAll(pageable);
        HashMap<String, Object> map = listExpenseToMap(expenses.getContent());
        map.put("totalElement", expenses.getTotalElements());
        map.put("totalPage", expenses.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Sort sort) {
        return listExpenseToMap(expenseRepository.findAll(sort));
    }

    @Override
    public ExpenseDTO findById(int id) throws CustomNotFoundException {
        return ExpenseMapper.toExpenseDTO(optionalValidate.getExpenseById(id));
    }

    @Override
    public ExpenseDTO create(ExpenseDTOCreate expenseDTOCreate) throws CustomNotFoundException {
        Expense expense = ExpenseMapper.toExpense(expenseDTOCreate);
        expense.setStation(optionalValidate.getStationById(expenseDTOCreate.getStationId()));
        expense.setFuelImport(optionalValidate.getFuelImportById(expenseDTOCreate.getFuelImportId()));
        expense = expenseRepository.save(expense);
        return ExpenseMapper.toExpenseDTO(expense);
    }

    @Override
    public ExpenseDTO update(int id, ExpenseDTOUpdate expenseDTOUpdate) throws CustomNotFoundException {
        Expense oldExpense = optionalValidate.getExpenseById(id);
        ExpenseMapper.copyNonNullToExpense(oldExpense, expenseDTOUpdate);
        Integer stationId = expenseDTOUpdate.getStationId();
        Integer fuelImportId = expenseDTOUpdate.getFuelImportId();
        if (stationId != null) {
            oldExpense.setStation(optionalValidate.getStationById(stationId));
        }
        if (fuelImportId != null) {
            oldExpense.setFuelImport(optionalValidate.getFuelImportById(fuelImportId));
        }
        oldExpense = expenseRepository.save(oldExpense);
        return ExpenseMapper.toExpenseDTO(oldExpense);
    }


    @Override
    public ExpenseDTO delete(int id) throws CustomNotFoundException {
        Expense expense = optionalValidate.getExpenseById(id);
        expenseRepository.delete(expense);
        return ExpenseMapper.toExpenseDTO(expense);
    }
}
