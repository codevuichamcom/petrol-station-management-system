package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTO;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOCreate;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOFilter;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOUpdate;

import java.util.HashMap;

public interface ExpenseService {
    HashMap<String, Object> findAll(ExpenseDTOFilter filter);

    ExpenseDTO findById(int id) throws CustomNotFoundException;

    ExpenseDTO create(ExpenseDTOCreate pumpDTOCreate) throws CustomNotFoundException;

    ExpenseDTO update(int id, ExpenseDTOUpdate pumpDTOUpdate) throws CustomNotFoundException;

    ExpenseDTO delete(int id) throws CustomNotFoundException;

}
