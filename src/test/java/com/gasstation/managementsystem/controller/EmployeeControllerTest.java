package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOCreate;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.service.impl.EmployeeServiceImpl;
import com.gasstation.managementsystem.utils.UserHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeControllerTest {
    @Mock
    EmployeeServiceImpl employeeService;
    @Mock
    UserHelper userHelper;
    @InjectMocks
    EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param login is ADMIN
     */
    @Test
    void getAll_UTCID01() {
        UserType userType = UserType.builder().id(1).type("ADMIN").build();
        User user = User.builder().id(1).name("ADMIN").userType(userType).build();
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(1).type("ADMIN").build();
        UserDTO userDTO = UserDTO.builder().id(1).name("ADMIN").userType(userTypeDTO).build();

        StationDTO stationDTO = StationDTO.builder().id(1).name("station").owner(userDTO).build();

        List<EmployeeDTO> employeeDTOList = IntStream.range(1, 10).mapToObj(i -> EmployeeDTO.builder()
                .id(i)
                .name("employee" + i)
                .address("Ha Noi")
                .phone("012345678" + i)
                .gender(true)
                .dateOfBirth(16094340000000L)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", employeeDTOList);

        Mockito.when(userHelper.getUserLogin()).thenReturn(user);
        Mockito.when(employeeService.findAll()).thenReturn(map);
        HashMap<String, Object> mapResult = employeeController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<EmployeeDTO> employeeDTOListResult = (List<EmployeeDTO>) mapResult.get("data");
        assertEquals(employeeDTOList.size(), employeeDTOListResult.size());
        for (int i = 0; i < employeeDTOListResult.size(); i++) {
            EmployeeDTO o1 = employeeDTOList.get(i);
            EmployeeDTO o2 = employeeDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    /**
     * param login is OWNER
     */
    @Test
    void getAll_UTCID02() {
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        User user = User.builder().id(2).name("OWNER").userType(userType).build();
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(2).name("OWNER").userType(userTypeDTO).build();

        StationDTO stationDTO = StationDTO.builder().id(1).name("station").owner(userDTO).build();

        List<EmployeeDTO> employeeDTOList = IntStream.range(1, 10).mapToObj(i -> EmployeeDTO.builder()
                .id(i)
                .name("employee" + i)
                .address("Ha Noi")
                .phone("012345678" + i)
                .gender(true)
                .dateOfBirth(16094340000000L)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", employeeDTOList);

        Mockito.when(userHelper.getUserLogin()).thenReturn(user);
        Mockito.when(employeeService.findAll()).thenReturn(map);
        Mockito.when(employeeService.findAllByOwnerId(2)).thenReturn(map);
        HashMap<String, Object> mapResult = employeeController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<EmployeeDTO> employeeDTOListResult = (List<EmployeeDTO>) mapResult.get("data");
        assertEquals(employeeDTOList.size(), employeeDTOListResult.size());
        for (int i = 0; i < employeeDTOListResult.size(); i++) {
            EmployeeDTO o1 = employeeDTOList.get(i);
            EmployeeDTO o2 = employeeDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    /**
     * param login is not ADMIN,OWNER
     * CASE : return new HashMap<>();
     */
    @Test
    void getAll_UTCID03() {
        UserType userType = UserType.builder().id(3).type("CUSTOMER").build();
        User user = User.builder().id(3).name("CUSTOMER").userType(userType).build();
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(3).type("CUSTOMER").build();
        UserDTO userDTO = UserDTO.builder().id(3).name("CUSTOMER").userType(userTypeDTO).build();

        StationDTO stationDTO = StationDTO.builder().id(1).name("station").owner(userDTO).build();

        List<EmployeeDTO> employeeDTOList = IntStream.range(1, 10).mapToObj(i -> EmployeeDTO.builder()
                .id(i)
                .name("employee" + i)
                .address("Ha Noi")
                .phone("012345678" + i)
                .gender(true)
                .dateOfBirth(16094340000000L)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", employeeDTOList);

        Mockito.when(userHelper.getUserLogin()).thenReturn(user);
        Mockito.when(employeeService.findAll()).thenReturn(map);
        Mockito.when(employeeService.findAllByOwnerId(2)).thenReturn(map);
        HashMap<String, Object> mapResult = employeeController.getAll();

//        assertTrue(mapResult.containsKey("data"));
//        List<EmployeeDTO> employeeDTOListResult = (List<EmployeeDTO>) mapResult.get("data");
//        assertEquals(employeeDTOList.size(), employeeDTOListResult.size());
//        for (int i = 0; i < employeeDTOListResult.size(); i++) {
//            EmployeeDTO o1 = employeeDTOList.get(i);
//            EmployeeDTO o2 = employeeDTOListResult.get(i);
//            assertEquals(o1, o2);
//        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(2).name("OWNER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).name("station").owner(userDTO).build();
        EmployeeDTO mockResult = EmployeeDTO.builder()
                .id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(16094340000000L)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build();
        Mockito.when(employeeService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, employeeController.getOne(1));
    }

    @Test
    void create() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(2).name("OWNER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).name("station").owner(userDTO).build();

        EmployeeDTO mockResult = EmployeeDTO.builder()
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(16094340000000L)
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
        Mockito.when(employeeService.create(employeeDTOCreate)).thenReturn(mockResult);
        assertEquals(mockResult, employeeController.create(employeeDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(2).name("OWNER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).name("station").owner(userDTO).build();

        EmployeeDTO mockResult = EmployeeDTO.builder()
                .id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(16094340000000L)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build();
        EmployeeDTOUpdate employeeDTOUpdate = EmployeeDTOUpdate.builder()
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber("12345678900")
                .stationId(1)
                .build();
        Mockito.when(employeeService.update(1, employeeDTOUpdate)).thenReturn(mockResult);
        assertEquals(mockResult, employeeController.update(1, employeeDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(2).name("OWNER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).name("station").owner(userDTO).build();

        EmployeeDTO mockResult = EmployeeDTO.builder()
                .id(1)
                .name("employee")
                .address("Ha Noi")
                .phone("0123456789")
                .gender(true)
                .dateOfBirth(16094340000000L)
                .identityCardNumber("12345678900")
                .station(stationDTO)
                .build();
        Mockito.when(employeeService.delete(1)).thenReturn(mockResult);
        assertEquals(mockResult, employeeController.delete(1));
    }
}