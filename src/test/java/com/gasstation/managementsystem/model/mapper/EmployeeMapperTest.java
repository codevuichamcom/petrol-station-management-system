package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Employee;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOCreate;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOUpdate;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {
    /**
     * param employee is null
     */
    @Test
    void toEmployeeDTO_UTCID01() {
        assertNull(EmployeeMapper.toEmployeeDTO(null));
    }

    /**
     * param employee is not null
     */
    @Test
    void toEmployeeDTO_UTCID02() {
        Long dateOfBirth = 16094340000000L;
        //given
        Station station = Station.builder().id(1).name("station").build();
        Employee employee = Employee.builder()
                .id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(station)
                .build();
        //when
        EmployeeDTO employeeDTO = EmployeeMapper.toEmployeeDTO(employee);
        //then
        assertEquals(1, employeeDTO.getId());
        assertEquals("employee", employeeDTO.getName());
        assertEquals("Ha Noi", employeeDTO.getAddress());
        assertEquals("0123456789", employeeDTO.getPhone());
        assertEquals(true, employeeDTO.getGender());
        assertEquals(dateOfBirth, employeeDTO.getDateOfBirth());
        assertEquals("12345678900", employeeDTO.getIdentityCardNumber());
        assertAll(() -> {
            assertEquals(1, employee.getStation().getId());
            assertEquals("station", employee.getStation().getName());
        });
    }

    /**
     * param employee is not null
     */
    @Test
    void toEmployee_UTCID01() {
        assertNull(EmployeeMapper.toEmployee(null));
    }

    /**
     * param employee is not null
     */
    @Test
    void toEmployee_UTCID02() {
        Long dateOfBirth = 16094340000000L;
        EmployeeDTOCreate employeeDTOCreate = EmployeeDTOCreate.builder()
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .build();
        Employee employee = EmployeeMapper.toEmployee(employeeDTOCreate);
        assertEquals("employee", employee.getName());
        assertEquals("Ha Noi", employee.getAddress());
        assertEquals("0123456789", employee.getPhone());
        assertEquals(true, employee.getGender());
        assertEquals(dateOfBirth, employee.getDateOfBirth());
        assertEquals("12345678900", employee.getIdentityCardNumber());

    }

    /**
     * param employee is null
     */
    @Test
    void copyNonNullToEmployee_UTCID01() {
        Long dateOfBirth = 16094340000000L;

        EmployeeDTOUpdate employeeDTOUpdate = EmployeeDTOUpdate.builder()
                .name("employee update")
                .address("Ha Noi update")
                .phone("0123456788")
                .gender(false)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678901")
                .build();
        try {
            EmployeeMapper.copyNonNullToEmployee(null, employeeDTOUpdate);
        } catch (Exception ex) {
            assertTrue(true);
        }

    }

    /**
     * param employee is not null
     */
    @Test
    void copyNonNullToEmployee_UTCID02() {
        Long dateOfBirth = 16094340000000L;
        Long dateOfBirthUpdate = 16094440000000L;

        Employee employee = Employee.builder()
                .id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .build();
        EmployeeDTOUpdate employeeDTOUpdate = EmployeeDTOUpdate.builder()
                .name("employee update")
                .address("Ha Noi update")
                .phone("0123456788")
                .gender(false)
                .dateOfBirth(dateOfBirthUpdate)
                .identityCardNumber("12345678901")
                .build();

        EmployeeMapper.copyNonNullToEmployee(employee, employeeDTOUpdate);
        assertEquals(1, employee.getId());
        assertEquals("employee update", employee.getName());
        assertEquals("Ha Noi update", employee.getAddress());
        assertEquals("0123456788", employee.getPhone());
        assertEquals(false, employee.getGender());
        assertEquals(dateOfBirthUpdate, employee.getDateOfBirth());
        assertEquals("12345678901", employee.getIdentityCardNumber());

    }
}