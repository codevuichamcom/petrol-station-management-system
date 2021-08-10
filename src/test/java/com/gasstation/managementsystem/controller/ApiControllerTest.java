package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.api.ApiDTO;
import com.gasstation.managementsystem.model.dto.api.ApiDTOUpdate;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.service.impl.ApiServiceImpl;
import com.gasstation.managementsystem.utils.UserHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiControllerTest {
    @Mock
    ApiServiceImpl apiService;

    @Mock
    UserHelper userHelper;

    @InjectMocks
    ApiController apiController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * user login is ADMIN
     */
    @Test
    void getAll_UTCID01() {
        UserType userType = UserType.builder().id(1).type("ADMIN").build();
        List<ApiDTO> apiDTOList = IntStream.range(1, 10).mapToObj(i -> ApiDTO.builder()
                .id(i)
                .method("method" + i).path("/path" + i)
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", apiDTOList);

        Mockito.when(userHelper.getUserTypeOfUserLogin()).thenReturn(userType);
        Mockito.when(apiService.findAll()).thenReturn(map);
        HashMap<String, Object> mapResult = apiController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<ApiDTO> apiDTOListResult = (List<ApiDTO>) mapResult.get("data");
        assertEquals(apiDTOList.size(), apiDTOListResult.size());
        for (int i = 0; i < apiDTOListResult.size(); i++) {
            ApiDTO o1 = apiDTOList.get(i);
            ApiDTO o2 = apiDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    /**
     * user login is not ADMIN
     */
    @Test
    void getAll_UTCID02() {
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        List<ApiDTO> apiDTOList = IntStream.range(1, 10).mapToObj(i -> ApiDTO.builder()
                .id(i)
                .method("method" + i).path("/path" + i)
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", apiDTOList);

        Mockito.when(userHelper.getUserTypeOfUserLogin()).thenReturn(userType);
        Mockito.when(apiService.findAllByUserTypeId(userType.getId())).thenReturn(map);
        HashMap<String, Object> mapResult = apiController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<ApiDTO> apiDTOListResult = (List<ApiDTO>) mapResult.get("data");
        assertEquals(apiDTOList.size(), apiDTOListResult.size());
        for (int i = 0; i < apiDTOListResult.size(); i++) {
            ApiDTO o1 = apiDTOList.get(i);
            ApiDTO o2 = apiDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        ApiDTO apiDTOMock = ApiDTO.builder()
                .id(1).method("GET").path("/users")
                .accessibleUserTypes(Arrays.asList(UserTypeDTO.builder()
                        .id(1).type("ADMIN").build())).build();
        Mockito.when(apiService.findById(1)).thenReturn(apiDTOMock);
        ApiDTO apiDTOResult = apiController.getOne(1);
        assertEquals(apiDTOMock, apiDTOResult);
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {

        ApiDTOUpdate apiDTOUpdate = ApiDTOUpdate.builder()
                .accessibleUserTypes(Arrays.asList(1, 2, 3, 4))
                .build();
        ApiDTO apiDTOMock = ApiDTO.builder().id(1).method("GET").path("/users").build();
        Mockito.when(apiService.update(1, apiDTOUpdate)).thenReturn(apiDTOMock);
        ApiDTO apiDTOResult = apiController.update(1, apiDTOUpdate);
        assertEquals(apiDTOMock, apiDTOResult);

    }

}