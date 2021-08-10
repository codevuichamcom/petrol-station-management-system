package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.service.UserTypeService;
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

class UserTypeControllerTest {
    @Mock
    UserTypeService userTypeService;

    @InjectMocks
    UserTypeController userTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        List<UserTypeDTO> userTypeDTOList = IntStream.range(1, 10).mapToObj(i -> UserTypeDTO.builder()
                .id(i)
                .type("TYPE" + i)
                .build()).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", userTypeDTOList);
        Mockito.when(userTypeService.findAll()).thenReturn(map);
        HashMap<String, Object> mapResult = userTypeController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<UserTypeDTO> userTypeDTOListResult = (List<UserTypeDTO>) mapResult.get("data");
        assertEquals(userTypeDTOList.size(), userTypeDTOListResult.size());
        for (int i = 0; i < userTypeDTOListResult.size(); i++) {
            UserTypeDTO o1 = userTypeDTOList.get(i);
            UserTypeDTO o2 = userTypeDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        UserTypeDTO mockResult = UserTypeDTO.builder()
                .id(1)
                .type("TYPE1")
                .build();
        Mockito.when(userTypeService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, userTypeController.getOne(1));
    }

    @Test
    void create() {
        UserType userType = UserType.builder()
                .id(1)
                .type("TYPE1")
                .build();
        UserTypeDTO mockResult = UserTypeDTO.builder()
                .id(1)
                .type("TYPE1")
                .build();
        Mockito.when(userTypeService.save(userType)).thenReturn(mockResult);
        assertEquals(mockResult, userTypeController.create(userType));
    }

    @Test
    void update() {
        UserType userType = UserType.builder()
                .id(1)
                .type("TYPE1")
                .build();
        UserTypeDTO mockResult = UserTypeDTO.builder()
                .id(1)
                .type("TYPE1")
                .build();
        Mockito.when(userTypeService.save(userType)).thenReturn(mockResult);
        assertEquals(mockResult, userTypeController.update(1, userType));
    }

    @Test
    void delete() throws CustomNotFoundException {
        UserTypeDTO mockResult = UserTypeDTO.builder()
                .id(1)
                .type("TYPE1")
                .build();
        Mockito.when(userTypeService.delete(1)).thenReturn(mockResult);
        assertEquals(mockResult, userTypeController.delete(1));
    }
}