package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Employee;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOCreate;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class EmployeeMapper {

    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        if (employee == null) return null;
        StationDTO stationDTO = (employee.getStation() != null) ? StationDTO.builder()
                .id(employee.getStation().getId())
                .name(employee.getStation().getName()).build() : null;
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .address(employee.getAddress())
                .phone(employee.getPhone())
                .gender(employee.getGender())
                .dateOfBirth(employee.getDateOfBirth())
                .identityCardNumber(employee.getIdentityCardNumber())
                .station(stationDTO).build();
    }

    public static Employee toEmployee(EmployeeDTOCreate employeeDTOCreate) {
        if (employeeDTOCreate == null) return null;
        return Employee.builder()
                .name(employeeDTOCreate.getName())
                .address(employeeDTOCreate.getAddress())
                .phone(employeeDTOCreate.getPhone())
                .gender(employeeDTOCreate.getGender())
                .dateOfBirth(employeeDTOCreate.getDateOfBirth())
                .identityCardNumber(employeeDTOCreate.getIdentityCardNumber()).build();
    }

    public static void copyNonNullToEmployee(Employee employee, EmployeeDTOUpdate employeeDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(employee, employeeDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
