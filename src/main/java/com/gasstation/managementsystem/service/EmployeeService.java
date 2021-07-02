package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOCreate;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOUpdate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public interface EmployeeService {
    HashMap<String, Object> findAll(Pageable pageable);

    HashMap<String, Object> findAll(Sort sort);

    EmployeeDTO findById(int id) throws CustomNotFoundException;

    EmployeeDTO create(EmployeeDTOCreate employeeDTOCreate) throws CustomDuplicateFieldException, CustomNotFoundException;

    EmployeeDTO update(int id, EmployeeDTOUpdate employeeDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException;

    EmployeeDTO delete(int id) throws CustomNotFoundException;
}
