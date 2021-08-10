package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Employee;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOCreate;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.mapper.EmployeeMapper;
import com.gasstation.managementsystem.repository.EmployeeRepository;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private OptionalValidate optionalValidate;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * non param
     */
    @Test
    void findAll() {
        List<Employee> mockRepository = new ArrayList<>();
        List<EmployeeDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);

        Mockito.when(employeeRepository.findAll()).thenReturn(mockRepository);
        List<EmployeeDTO> listResultService = (List<EmployeeDTO>) employeeService.findAll().get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            EmployeeDTO o1 = mockResult.get(i);
            EmployeeDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<Employee> mockRepository, List<EmployeeDTO> mockResult) {
        Long dateOfBirth = 16094340000000L;
        Station station = Station.builder().id(1).name("station").build();
        for (int i = 1; i <= 10; i++) {
            Employee employee = Employee.builder().id(i)
                    .name("employee")
                    .address("Ha Noi")
                    .phone("0123456789")
                    .gender(true)
                    .dateOfBirth(dateOfBirth)
                    .identityCardNumber("12345678900")
                    .station(station)
                    .build();
            mockRepository.add(employee);
            mockResult.add(EmployeeMapper.toEmployeeDTO(employee));
        }
    }

    @Test
    void findAllByOwnerId() {
        Long dateOfBirth = 16094340000000L;
        Station station = Station.builder().id(1).name("station").owner(User.builder().id(1).name("OWNER").build()).build();
        StationDTO stationDTO = StationDTO.builder().id(1).name("station").owner(UserDTO.builder().id(1).name("OWNER").build()).build();
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
        EmployeeDTO mockResult = EmployeeDTO.builder()
                .id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build();
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        Mockito.when(employeeRepository.findAllByOwnerId(1,Sort.by(Sort.Direction.ASC, "id"))).thenReturn(employeeList);
        List<EmployeeDTO> listResult = (List<EmployeeDTO>) employeeService.findAllByOwnerId(1).get("data");
        assertEquals(mockResult.getId(), listResult.get(0).getId());
        assertEquals(mockResult.getName(), listResult.get(0).getName());
        assertEquals(mockResult.getAddress(), listResult.get(0).getAddress());
        assertEquals(mockResult.getGender(), listResult.get(0).getGender());
        assertEquals(mockResult.getIdentityCardNumber(), listResult.get(0).getIdentityCardNumber());
    }

    @Test
    void findById() throws CustomNotFoundException {
        Long dateOfBirth = 16094340000000L;
        Station station = Station.builder().id(1).name("station").build();
        StationDTO stationDTO = StationDTO.builder().id(1).name("station").build();
        Employee mockRepository = Employee.builder().id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(station)
                .build();
        EmployeeDTO mockResult = EmployeeDTO.builder().id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build();
        Mockito.when(optionalValidate.getEmployeeById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, employeeService.findById(1));
    }

    @Test
    void create_UTCID01() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        Station station = Station.builder().id(1).name("station").build();
        StationDTO stationDTO = StationDTO.builder().id(1).name("station").build();
        Employee mockRepository = Employee.builder().id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(station)
                .build();
        EmployeeDTO mockResult = EmployeeDTO.builder().id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build();
        EmployeeDTOCreate employeeDTOCreate = EmployeeDTOCreate.builder()
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .stationId(1)
                .build();
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(station);
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(mockRepository);
        assertEquals(mockResult, employeeService.create(employeeDTOCreate));
    }

    /**
     * check duplicate phone
     */
    @Test
    void create_UTCID02() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        Station station = Station.builder().id(1).name("station").build();
        Employee mockRepository = Employee.builder().id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(station)
                .build();
        EmployeeDTOCreate employeeDTOCreate = EmployeeDTOCreate.builder()
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .stationId(1)
                .build();
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(station);
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(mockRepository);
        Optional<Employee> employeeOptional = Optional.of(mockRepository);
        Mockito.when(employeeRepository.findByPhone("0123456789")).thenReturn(employeeOptional);
        assertThrows(CustomDuplicateFieldException.class, () -> {
            employeeService.create(employeeDTOCreate);
        });
    }

    /**
     * check duplicate identityCardNumber
     */
    @Test
    void create_UTCID03() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        Station station = Station.builder().id(1).name("station").build();
        Employee mockRepository = Employee.builder().id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(station)
                .build();
        EmployeeDTOCreate employeeDTOCreate = EmployeeDTOCreate.builder()
                .name("employee")
                .address("Ha Noi")
                .phone("0123456799")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .stationId(1)
                .build();
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(station);
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(mockRepository);
        Optional<Employee> employeeOptional = Optional.of(mockRepository);
        Mockito.when(employeeRepository.findByIdentityCardNumber("12345678900")).thenReturn(employeeOptional);
        assertThrows(CustomDuplicateFieldException.class, () -> {
            employeeService.create(employeeDTOCreate);
        });
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        Long dateOfBirth_update = 16094540000000L;
        Station station = Station.builder().id(1).name("station").build();
        StationDTO stationDTO = StationDTO.builder().id(1).name("station").build();
        Employee mockRepository = Employee.builder().id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(station)
                .build();
        EmployeeDTO mockResult = EmployeeDTO.builder().id(1)
                .name("Employee")
                .address("Ha Noi 2")
                .phone("0123456789")
                .gender(false)
                .dateOfBirth(dateOfBirth_update)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build();
        EmployeeDTOUpdate employeeDTOUpdate = EmployeeDTOUpdate.builder()
                .name("Employee")
                .address("Ha Noi 2")
                .phone("0123456789")
                .gender(false)
                .dateOfBirth(dateOfBirth_update)
                .identityCardNumber("12345678900")
                .stationId(1)
                .build();
        Mockito.when(optionalValidate.getEmployeeById(1)).thenReturn(mockRepository);
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(station);
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(mockRepository);
        assertEquals(mockResult, employeeService.update(1, employeeDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        Long dateOfBirth = 16094340000000L;
        Station station = Station.builder().id(1).name("station").build();
        StationDTO stationDTO = StationDTO.builder().id(1).name("station").build();
        Employee mockRepository = Employee.builder().id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(station)
                .build();
        EmployeeDTO mockResult = EmployeeDTO.builder().id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build();
        Mockito.when(optionalValidate.getEmployeeById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, employeeService.delete(1));
    }

}