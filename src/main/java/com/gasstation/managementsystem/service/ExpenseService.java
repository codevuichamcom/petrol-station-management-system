package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTO;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOCreate;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOUpdate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public interface ExpenseService {
    HashMap<String, Object> findAll(Pageable pageable);

    HashMap<String, Object> findAll(Sort sort);

    ExpenseDTO findById(int id) throws CustomNotFoundException;

    ExpenseDTO create(ExpenseDTOCreate pumpDTOCreate) throws CustomNotFoundException;

    ExpenseDTO update(int id, ExpenseDTOUpdate pumpDTOUpdate) throws CustomNotFoundException;

    ExpenseDTO delete(int id) throws CustomNotFoundException;

}
