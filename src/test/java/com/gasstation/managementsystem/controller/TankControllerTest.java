package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTOCreate;
import com.gasstation.managementsystem.model.dto.tank.TankDTOUpdate;
import com.gasstation.managementsystem.service.impl.TankServiceImpl;
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

class TankControllerTest {
    @Mock
    TankServiceImpl tankService;

    @InjectMocks
    TankController tankController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        StationDTO stationDTO = StationDTO.builder().id(1).build();
        List<TankDTO> tankDTOList = IntStream.range(1, 10).mapToObj(i -> TankDTO.builder()
                .id(i)
                .name("tank" + i)
                .station(stationDTO)
                .volume(333d)
                .currentPrice(444d)
                .remain(0d)
                .build()).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", tankDTOList);
        Mockito.when(tankService.findAll()).thenReturn(map);
        HashMap<String, Object> mapResult = tankController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<TankDTO> TankDTOListResult = (List<TankDTO>) mapResult.get("data");
        assertEquals(tankDTOList.size(), TankDTOListResult.size());
        for (int i = 0; i < TankDTOListResult.size(); i++) {
            TankDTO o1 = tankDTOList.get(i);
            TankDTO o2 = TankDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        StationDTO stationDTO = StationDTO.builder().id(1).build();
        TankDTO mockResult = TankDTO.builder().name("tank1")
                .station(stationDTO)
                .volume(333d)
                .currentPrice(444d)
                .remain(0d)
                .build();
        Mockito.when(tankService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, tankController.getOne(1));
    }

    @Test
    void create() throws CustomNotFoundException, CustomDuplicateFieldException {
        StationDTO stationDTO = StationDTO.builder().id(1).name("Hoang Long").build();
        FuelDTO fuelDTO = FuelDTO.builder().id(1).name("Ron95").build();
        TankDTO mockResult = TankDTO.builder()
                .name("tank1")
                .station(stationDTO)
                .fuel(fuelDTO)
                .volume(333d)
                .currentPrice(444d)
                .remain(0d)
                .build();

        TankDTOCreate tankDTOCreate = TankDTOCreate.builder()
                .name("tank1")
                .stationId(1)
                .fuelId(1)
                .volume(333d)
                .currentPrice(444d)
                // mac dinh remain = 0
                .build();

        Mockito.when(tankService.create(tankDTOCreate)).thenReturn(mockResult);
        assertEquals(mockResult, tankController.create(tankDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        StationDTO stationDTO = StationDTO.builder().id(1).name("Hoang Long").build();
        FuelDTO fuelDTO = FuelDTO.builder().id(1).name("Ron95").build();
        TankDTO mockResult = TankDTO.builder()
                .name("tank1")
                .station(stationDTO)
                .fuel(fuelDTO)
                .volume(444d)
                .currentPrice(333d)
                .remain(0d)
                .build();

        TankDTOUpdate tankDTOUpdate = TankDTOUpdate.builder()
                .name("tank1")
                .stationId(1)
                .fuelId(1)
                .volume(444d)
                .currentPrice(333d)
                .remain(0d)
                .build();

        Mockito.when(tankService.update(1, tankDTOUpdate)).thenReturn(mockResult);
        assertEquals(mockResult, tankController.update(1, tankDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        StationDTO stationDTO = StationDTO.builder().id(1).name("Hoang Long").build();
        FuelDTO fuelDTO = FuelDTO.builder().id(1).name("Ron95").build();
        TankDTO mockResult = TankDTO.builder()
                .name("tank1")
                .station(stationDTO)
                .fuel(fuelDTO)
                .volume(444d)
                .currentPrice(333d)
                .remain(0d)
                .build();

        Mockito.when(tankService.delete(1)).thenReturn(mockResult);
        assertEquals(mockResult, tankController.delete(1));
    }
}