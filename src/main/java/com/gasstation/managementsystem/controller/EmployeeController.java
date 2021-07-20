package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOCreate;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOUpdate;
import com.gasstation.managementsystem.service.EmployeeService;
import com.gasstation.managementsystem.utils.UserHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping(value = Api.PREFIX)
@CrossOrigin
@Tag(name = "Employee", description = "API for Employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserHelper userHelper;

    @Operation(summary = "View All employee")
    @GetMapping("/employees")
    public HashMap<String, Object> getAll() {
        User user = userHelper.getUserLogin();
        UserType userType = user.getUserType();
        switch (userType.getId()) {
            case UserType.ADMIN:
                return employeeService.findAll();
            case UserType.OWNER:
                return employeeService.findAllByOwnerId(user.getId());
        }
        return new HashMap<>();
    }

    @Operation(summary = "Find employee by id")
    @GetMapping("/employees/{id}")
    public EmployeeDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return employeeService.findById(id);
    }

    @Operation(summary = "Create new employee")
    @PostMapping("/employees")
    public EmployeeDTO create(@Valid @RequestBody EmployeeDTOCreate employeeDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return employeeService.create(employeeDTOCreate);
    }

    @Operation(summary = "Update employee by id")
    @PutMapping("/employees/{id}")
    public EmployeeDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody EmployeeDTOUpdate employeeDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return employeeService.update(id, employeeDTOUpdate);
    }

    @Operation(summary = "Delete employee by id")
    @DeleteMapping("/employees/{id}")
    public EmployeeDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return employeeService.delete(id);
    }
}
