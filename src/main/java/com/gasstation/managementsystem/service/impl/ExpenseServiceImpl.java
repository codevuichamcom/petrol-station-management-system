package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Expense;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTO;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOCreate;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOFilter;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOUpdate;
import com.gasstation.managementsystem.model.mapper.ExpenseMapper;
import com.gasstation.managementsystem.repository.ExpenseRepository;
import com.gasstation.managementsystem.repository.criteria.ExpenseRepositoryCriteria;
import com.gasstation.managementsystem.service.ExpenseService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;
    private final ExpenseRepositoryCriteria expenseCriteria;

    @Override
    public HashMap<String, Object> findAll(ExpenseDTOFilter filter) {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        if (userType.getId() == UserType.OWNER) {
            filter.setOwnerId(userLoggedIn.getId());
        }
        HashMap<String, Object> temp = expenseCriteria.findAll(filter);
        List<Expense> expenseList = (List<Expense>) temp.get("data");
        List<ExpenseDTO> expenseDTOS = expenseList.stream().map(ExpenseMapper::toExpenseDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", expenseDTOS);
        map.put("totalElement", temp.get("totalElement"));
        map.put("totalPage", temp.get("totalPage"));
        return map;
    }

    @Override
    public ExpenseDTO findById(int id) throws CustomNotFoundException {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        Expense expense = optionalValidate.getExpenseById(id);
        if (userType.getId() == UserType.OWNER && !userLoggedIn.getId().equals(expense.getStation().getOwner().getId())) {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found")
                    .message("Expense not of the owner")
                    .table("expense_tbl").build());
        }
        return ExpenseMapper.toExpenseDTO(expense);
    }

    @Override
    public ExpenseDTO create(ExpenseDTOCreate expenseDTOCreate) throws CustomNotFoundException {
        Expense expense = ExpenseMapper.toExpense(expenseDTOCreate);
        expense.setStation(optionalValidate.getStationById(expenseDTOCreate.getStationId()));
        if (expenseDTOCreate.getFuelImportId() != null) {
            expense.setFuelImport(optionalValidate.getFuelImportById(expenseDTOCreate.getFuelImportId()));
        }
        expense.setCreator(userHelper.getUserLogin());
        trimString(expense);
        expense = expenseRepository.save(expense);
        return ExpenseMapper.toExpenseDTO(expense);
    }

    private void trimString(Expense expense) {
        expense.setReason(StringUtils.trim(expense.getReason()));
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
        trimString(oldExpense);
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
