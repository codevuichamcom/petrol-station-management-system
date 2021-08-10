package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.mapper.ShiftMapper;
import com.gasstation.managementsystem.repository.ShiftRepository;
import com.gasstation.managementsystem.utils.OptionalValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShiftServiceImplTest {
    @Mock
    private ShiftRepository shiftRepository;

    @Mock
    private OptionalValidate optionalValidate;

    @InjectMocks
    private ShiftServiceImpl shiftService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Shift> mockRepository = new ArrayList<>();
        List<ShiftDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);
        Sort sort = null;
        Mockito.when(shiftRepository.findAll(sort)).thenReturn(mockRepository);

        List<ShiftDTO> listResultService = (List<ShiftDTO>) shiftService.findAll(sort).get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            ShiftDTO o1 = mockResult.get(i);
            ShiftDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<Shift> mockRepository, List<ShiftDTO> mockResult) {
        Long startTime = 25200000L;
        Long endTime = 43200000L;
        for (int i = 1; i <= 10; i++) {
            Shift shift = Shift.builder()
                    .id(1)
                    .name("Morning")
                    .startTime(startTime)
                    .endTime(endTime)
                    .station(Station.builder()
                            .id(1)
                            .name("station")
                            .address("address")
                            .owner(User.builder()
                                    .id(1)
                                    .name("owner")
                                    .build())
                            .build())
                    .build();
            mockRepository.add(shift);
            mockResult.add(ShiftMapper.toShiftDTO(shift));
        }
    }

    @Test
    void findById() throws CustomNotFoundException {
        Long startTime = 25200000L;
        Long endTime = 43200000L;
        Shift shift = Shift.builder()
                .id(1)
                .name("Morning")
                .startTime(startTime)
                .endTime(endTime)
                .station(Station.builder()
                        .id(1)
                        .name("station")
                        .address("address")
                        .owner(User.builder()
                                .id(1)
                                .name("owner")
                                .build())
                        .build())
                .build();

        ShiftDTO mockResult = ShiftDTO.builder()
                .id(1)
                .name("Morning")
                .startTime("07:00")
                .endTime("12:00")
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
        Mockito.when(optionalValidate.getShiftById(1)).thenReturn(shift);
        assertEquals(mockResult, shiftService.findById(1));
    }

    @Test
    void create() throws CustomNotFoundException {
        Station station = Station.builder()
                .id(1)
                .name("station")
                .address("address")
                .owner(User.builder()
                        .id(1)
                        .name("owner")
                        .build()).build();
        Long startTime = 25200000L;
        Long endTime = 43200000L;
        Shift shift = Shift.builder()
                .id(1)
                .name("Morning")
                .startTime(startTime)
                .endTime(endTime)
                .station(station)
                .build();

        ShiftDTO mockResult = ShiftDTO.builder()
                .id(1)
                .name("Morning")
                .startTime("07:00")
                .endTime("12:00")
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
                .stationId(1)
                .startTime("07:00")
                .endTime("12:00")
                .stationId(1)
                .build();
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(station);
        Mockito.when(shiftRepository.save(Mockito.any(Shift.class))).thenReturn(shift);
        assertEquals(mockResult, shiftService.create(shiftDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException {
        Station station = Station.builder()
                .id(1)
                .name("station")
                .address("address")
                .owner(User.builder()
                        .id(1)
                        .name("owner")
                        .build()).build();
        Long startTime = 25200000L;
        Long endTime = 43200000L;
        Shift shift = Shift.builder()
                .id(1)
                .name("Morning")
                .startTime(startTime)
                .endTime(endTime)
                .station(station)
                .build();

        ShiftDTO mockResult = ShiftDTO.builder()
                .id(1)
                .name("Morning")
                .startTime("07:30")
                .endTime("12:30")
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
                .endTime("12:30")
                .build();
        Mockito.when(optionalValidate.getShiftById(1)).thenReturn(shift);
        Mockito.when(shiftRepository.save(Mockito.any(Shift.class))).thenReturn(shift);
        assertEquals(mockResult, shiftService.update(1, shiftDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        Station station = Station.builder()
                .id(1)
                .name("station")
                .address("address")
                .owner(User.builder()
                        .id(1)
                        .name("owner")
                        .build()).build();
        Long startTime = 25200000L;
        Long endTime = 43200000L;
        Shift shift = Shift.builder()
                .id(1)
                .name("Morning")
                .startTime(startTime)
                .endTime(endTime)
                .station(station)
                .build();

        ShiftDTO mockResult = ShiftDTO.builder()
                .id(1)
                .name("Morning")
                .startTime("07:00")
                .endTime("12:00")
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
        Mockito.when(optionalValidate.getShiftById(1)).thenReturn(shift);
        assertEquals(mockResult, shiftService.delete(1));
    }
}