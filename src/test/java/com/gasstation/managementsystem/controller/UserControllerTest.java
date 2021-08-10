package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.service.impl.UserServiceImpl;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserControllerTest {
    @Mock
    UserServiceImpl userService;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param userTypeId is null
     */
    @Test
    void getAll_UTCID01() {
        List<UserDTO> userDTOList = IntStream.range(1, 10).mapToObj(i -> UserDTO.builder()
                .id(i)
                .name("user_" + i)
                .gender(true)
                .dateOfBirth(16094340000000L)
                .build()).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", userDTOList);
        Mockito.when(userService.findAll()).thenReturn(map);
        HashMap<String, Object> mapResult = userController.getAll(null);

        assertTrue(mapResult.containsKey("data"));
        List<UserDTO> UserDTOListResult = (List<UserDTO>) mapResult.get("data");
        assertEquals(userDTOList.size(), UserDTOListResult.size());
        for (int i = 0; i < UserDTOListResult.size(); i++) {
            UserDTO o1 = userDTOList.get(i);
            UserDTO o2 = UserDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    /**
     * param userTypeId is not null
     */
    @Test
    void getAll_UTCID02() {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(3).type("CUSTOMER").build();
        List<UserDTO> userDTOList = IntStream.range(1, 10).mapToObj(i -> UserDTO.builder()
                .id(i)
                .name("user_" + i)
                .gender(true)
                .dateOfBirth(16094340000000L)
                .userType(userTypeDTO)
                .build()).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", userDTOList);
        Mockito.when(userService.findByUserTypeId(3)).thenReturn(map);
        Mockito.when(userService.findAll()).thenReturn(map);
        HashMap<String, Object> mapResult = userController.getAll(3);

        assertTrue(mapResult.containsKey("data"));
        List<UserDTO> UserDTOListResult = (List<UserDTO>) mapResult.get("data");
        assertEquals(userDTOList.size(), UserDTOListResult.size());
        for (int i = 0; i < UserDTOListResult.size(); i++) {
            UserDTO o1 = userDTOList.get(i);
            UserDTO o2 = UserDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(3).type("CUSTOMER").build();
        UserDTO mockResult = UserDTO.builder()
                .id(1)
                .name("user_1")
                .dateOfBirth(16094340000000L)
                .userType(userTypeDTO)
                .build();
        Mockito.when(userService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, userController.getOne(1));
    }

    @Test
    void create() throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException {
        Long dateOfBirth = 16094340000000L;
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(3).type("CUSTOMER").build();
        UserDTO mockResult = UserDTO.builder()
                .name("user_1")
                .userType(userTypeDTO)
                .dateOfBirth(16094340000000L)
                .build();

        UserDTOCreate userDTOCreate = UserDTOCreate.builder()
                .name("user_1")
                .userTypeId(3)
                .dateOfBirth(dateOfBirth)
                .build();
        Mockito.when(userService.create(userDTOCreate)).thenReturn(mockResult);
        assertEquals(mockResult, userController.create(userDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException {
       Long dateOfBirth = 16094340000000L;
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(3).type("CUSTOMER").build();
        UserDTO mockResult = UserDTO.builder()
                .name("user_1")
                .userType(userTypeDTO)
                .dateOfBirth(16094340000000L)
                .build();

        UserDTOUpdate userDTOUpdate = UserDTOUpdate.builder()
                .name("user_1")
                .userTypeId(3)
                .dateOfBirth(dateOfBirth)
                .build();
        Mockito.when(userService.update(1, userDTOUpdate)).thenReturn(mockResult);
        assertEquals(mockResult, userController.update(1, userDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(3).type("CUSTOMER").build();
        UserDTO mockResult = UserDTO.builder()
                .name("user_1")
                .userType(userTypeDTO)
                .dateOfBirth(16094340000000L)
                .build();
        Mockito.when(userService.delete(1)).thenReturn(mockResult);
        assertEquals(mockResult, userController.delete(1));
    }
}