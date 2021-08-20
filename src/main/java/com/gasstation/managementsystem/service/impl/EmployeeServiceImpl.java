package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Employee;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOCreate;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOUpdate;
import com.gasstation.managementsystem.model.mapper.EmployeeMapper;
import com.gasstation.managementsystem.repository.EmployeeRepository;
import com.gasstation.managementsystem.service.EmployeeService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;

    private HashMap<String, Object> listEmployeeToMap(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = employees.stream().map(EmployeeMapper::toEmployeeDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", employeeDTOS);
        return map;
    }


    @Override
    public HashMap<String, Object> findAll() {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        List<Employee> employeeList = new ArrayList<>();
        switch (userType.getId()) {
            case UserType.ADMIN:
                employeeList = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
                break;
            case UserType.OWNER:
                employeeList = employeeRepository.findAllByOwnerId(userLoggedIn.getId(), Sort.by(Sort.Direction.ASC, "id"));
        }
        return listEmployeeToMap(employeeList);
    }


    @Override
    public EmployeeDTO findById(int id) throws CustomNotFoundException {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        Employee employee = optionalValidate.getEmployeeById(id);
        if (userType.getId() == UserType.OWNER && !userLoggedIn.getId().equals(employee.getStation().getOwner().getId())) {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found")
                    .message("Employee not of the owner")
                    .table("employee_tbl").build());
        }
        return EmployeeMapper.toEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO create(EmployeeDTOCreate employeeDTOCreate) throws CustomDuplicateFieldException, CustomNotFoundException {
        checkDuplicate(employeeDTOCreate.getPhone(), employeeDTOCreate.getIdentityCardNumber());
        Employee employee = EmployeeMapper.toEmployee(employeeDTOCreate);
        Station station = optionalValidate.getStationById(employeeDTOCreate.getStationId());
        employee.setStation(station);
        trimString(employee);
        employee = employeeRepository.save(employee);
        return EmployeeMapper.toEmployeeDTO(employee);
    }

    private void trimString(Employee employee) {
        employee.setName(StringUtils.trim(employee.getName()));
        employee.setAddress(StringUtils.trim(employee.getAddress()));
    }

    private void checkDuplicate(String phone, String identityCardNumber) throws CustomDuplicateFieldException {
        phone = StringUtils.trim(phone);
        identityCardNumber = StringUtils.trim(identityCardNumber);
        if (phone != null) {
            Optional<Employee> employee = employeeRepository.findByPhone(phone);
            if (employee.isPresent()) {
                throw new CustomDuplicateFieldException(CustomError.builder()
                        .code("duplicate").field("phone").message("Phone is duplicate").table("employee_table").build());
            }
        }
        if (identityCardNumber != null) {
            Optional<Employee> employee = employeeRepository.findByIdentityCardNumber(identityCardNumber);
            if (employee.isPresent()) {
                throw new CustomDuplicateFieldException(CustomError.builder().code("duplicate")
                        .field("identityCardNumber").message("Duplicate field").build());
            }
        }

    }

    @Override
    public EmployeeDTO update(int id, EmployeeDTOUpdate employeeDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Employee oldEmployee = optionalValidate.getEmployeeById(id);
        String phone = StringUtils.trim(employeeDTOUpdate.getPhone());
        String identityCardNumber = StringUtils.trim(employeeDTOUpdate.getIdentityCardNumber());
        if (phone != null && phone.equalsIgnoreCase(oldEmployee.getPhone())) {
            phone = null;
        }
        if (identityCardNumber != null && identityCardNumber.equalsIgnoreCase(oldEmployee.getIdentityCardNumber())) {
            identityCardNumber = null;
        }
        checkDuplicate(phone, identityCardNumber);
        EmployeeMapper.copyNonNullToEmployee(oldEmployee, employeeDTOUpdate);
        trimString(oldEmployee);
        oldEmployee = employeeRepository.save(oldEmployee);
        return EmployeeMapper.toEmployeeDTO(oldEmployee);
    }

    @Override
    public EmployeeDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException {
        Employee employee = optionalValidate.getEmployeeById(id);
        try {
            employeeRepository.delete(employee);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("fk_constraint")
                    .field("Employee_id")
                    .message("Employee in use").build());
        }
        return EmployeeMapper.toEmployeeDTO(employee);
    }

}
