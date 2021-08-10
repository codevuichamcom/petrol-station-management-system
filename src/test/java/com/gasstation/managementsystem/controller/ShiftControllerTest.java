package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.service.impl.ShiftServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShiftControllerTest {

    @Mock
    ShiftServiceImpl shiftService;

    @InjectMocks
    ShiftController shiftController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        //given
        List<ShiftDTO> fuelDTOList = IntStream.range(1, 10).mapToObj(i -> ShiftDTO.builder()
                .id(i)
                .name("Morning")
                .startTime("07:30")
                .endTime("11:30")
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .address("address")
                        .owner(UserDTO.builder()
                                .id(1)
                                .name("owner")
                                .build())
                        .build())
                .build()).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelDTOList);
        Mockito.when(shiftService.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(map);
        HashMap<String, Object> mapResult = shiftController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<ShiftDTO> shiftDTOSListResult = (List<ShiftDTO>) mapResult.get("data");
        assertEquals(fuelDTOList.size(), shiftDTOSListResult.size());
        for (int i = 0; i < shiftDTOSListResult.size(); i++) {
            ShiftDTO o1 = fuelDTOList.get(i);
            ShiftDTO o2 = shiftDTOSListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        ShiftDTO mockResult = ShiftDTO.builder()
                .id(1)
                .name("Morning")
                .startTime("07:30")
                .endTime("11:30")
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .address("address")
                        .owner(UserDTO.builder()
                                .id(1)
                                .name("owner")
                                .build())
                        .build())
                .build();
        Mockito.when(shiftService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, shiftController.getOne(1));
    }

    @Test
    void create() throws CustomNotFoundException {
        ShiftDTO mockResult = ShiftDTO.builder()
                .id(1)
                .name("Morning")
                .startTime("07:30")
                .endTime("11:30")
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .address("address")
                        .owner(UserDTO.builder()
                                .id(1)
                                .name("owner")
                                .build())
                        .build())
                .build();
        ShiftDTOCreate shiftDTOCreate = ShiftDTOCreate.builder()
                .name("Morning")
                .startTime("07:30")
                .endTime("11:30")
                .stationId(1)
                .build();
        Mockito.when(shiftService.create(shiftDTOCreate)).thenReturn(mockResult);
        assertEquals(mockResult, shiftController.create(shiftDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException {
        ShiftDTO mockResult = ShiftDTO.builder()
                .id(1)
                .name("Morning")
                .startTime("07:30")
                .endTime("11:30")
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .address("address")
                        .owner(UserDTO.builder()
                                .id(1)
                                .name("owner")
                                .build())
                        .build())
                .build();
        ShiftDTOUpdate shiftDTOUpdate = ShiftDTOUpdate.builder()
                .name("Morning")
                .startTime("07:30")
                .endTime("11:30")
                .build();
        Mockito.when(shiftService.update(1, shiftDTOUpdate)).thenReturn(mockResult);
        assertEquals(mockResult, shiftController.update(1, shiftDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        ShiftDTO mockResult = ShiftDTO.builder()
                .id(1)
                .name("Morning")
                .startTime("07:30")
                .endTime("11:30")
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .address("address")
                        .owner(UserDTO.builder()
                                .id(1)
                                .name("owner")
                                .build())
                        .build())
                .build();
        Mockito.when(shiftService.delete(1)).thenReturn(mockResult);
        assertEquals(mockResult, shiftController.delete(1));
    }
}