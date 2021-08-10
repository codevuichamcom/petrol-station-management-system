package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOCreate;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOUpdate;
import com.gasstation.managementsystem.service.impl.FuelServiceImpl;
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

class FuelControllerTest {
    @Mock
    FuelServiceImpl fuelService;

    @InjectMocks
    FuelController fuelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        List<FuelDTO> fuelDTOList = IntStream.range(1, 10).mapToObj(i -> FuelDTO.builder()
                .id(i)
                .name("fuel" + i)
                .unit("litter")
                .type("Xăng")
                .build()).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelDTOList);
        Mockito.when(fuelService.findAll()).thenReturn(map);
        HashMap<String, Object> mapResult = fuelController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<FuelDTO> fuelDTOListResult = (List<FuelDTO>) mapResult.get("data");
        assertEquals(fuelDTOList.size(), fuelDTOListResult.size());
        for (int i = 0; i < fuelDTOListResult.size(); i++) {
            FuelDTO o1 = fuelDTOList.get(i);
            FuelDTO o2 = fuelDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        FuelDTO mockResult = FuelDTO.builder()
                .id(1)
                .name("fuel1")
                .unit("litter")
                .type("Xăng")
                .build();
        Mockito.when(fuelService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, fuelController.getOne(1));
    }

    @Test
    void create() throws CustomDuplicateFieldException {
        FuelDTO mockResult = FuelDTO.builder()
                .id(1)
                .name("fuel1")
                .unit("litter")
                .type("Xăng")
                .build();
        FuelDTOCreate fuelDTOCreate = FuelDTOCreate.builder()
                .name("fuel1")
                .unit("litter")
                .type("Xăng")
                .build();
        Mockito.when(fuelService.create(fuelDTOCreate)).thenReturn(mockResult);
        assertEquals(mockResult, fuelController.create(fuelDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        FuelDTO mockResult = FuelDTO.builder()
                .id(1)
                .name("fuel1")
                .unit("litter")
                .type("Xăng")
                .build();
        FuelDTOUpdate fuelDTOUpdate = FuelDTOUpdate.builder()
                .name("fuel1")
                .unit("litter")
                .type("Xăng")
                .build();
        Mockito.when(fuelService.update(1, fuelDTOUpdate)).thenReturn(mockResult);
        assertEquals(mockResult, fuelController.update(1, fuelDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        FuelDTO mockResult = FuelDTO.builder()
                .id(1)
                .name("fuel1")
                .unit("litter")
                .type("Xăng")
                .build();
        Mockito.when(fuelService.delete(1)).thenReturn(mockResult);
        assertEquals(mockResult, fuelController.delete(1));
    }
}