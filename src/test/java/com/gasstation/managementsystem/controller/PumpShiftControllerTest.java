package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTO;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTOFilter;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.service.impl.PumpShiftServiceImpl;
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

class PumpShiftControllerTest {

    @Mock
    PumpShiftServiceImpl handOverShiftService;
    @InjectMocks
    PumpShiftController pumpShiftController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        Long createDate = 16094340000000L;
        Long closedTime = 16094440000000L;
        List<PumpShiftDTO> pumpShiftDTOList = IntStream.range(1, 10).mapToObj(i -> PumpShiftDTO.builder()
                .id(i)
                .shift(ShiftDTO.builder().id(1).station(StationDTO.builder().id(1).build()).build())
                .pump(PumpDTO.builder().id(1).build())
                .executor(UserDTO.builder().id(3).name("EMPLOYEE").build())
                .closedTime(closedTime)
                .createdDate(createDate)
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", pumpShiftDTOList);

        final int PAGE_INDEX = 1;
        final int PAGE_SIZE = 3;
        Integer[] shiftIds = new Integer[1];
        shiftIds[0] = 1;
        Integer[] pumpIds = new Integer[1];
        pumpIds[0] = 1;
        Integer[] stationIds = new Integer[1];
        stationIds[0] = 1;

        PumpShiftDTOFilter filter = PumpShiftDTOFilter.builder()
                .pageIndex(PAGE_INDEX)
                .pageSize(PAGE_SIZE)
                .createdDate(createDate)
                .closedTime(closedTime)
                .shiftIds(shiftIds)
                .pumpIds(pumpIds)
                .stationIds(stationIds)
                .executorName("EMPLOYEE").build();

        Mockito.when(handOverShiftService.findAll(filter)).thenReturn(map);

        HashMap<String, Object> mapResult = pumpShiftController.getAll(PAGE_INDEX, PAGE_SIZE, createDate, closedTime, shiftIds, pumpIds, stationIds, "EMPLOYEE",null);
//
//        assertTrue(mapResult.containsKey("data"));
//        List<HandOverShiftDTO> handOverShiftDTOSListResult = (List<HandOverShiftDTO>) mapResult.get("data");
//        assertEquals(handOverShiftDTOList.size(), handOverShiftDTOSListResult.size());
//        for (int i = 0; i < handOverShiftDTOSListResult.size(); i++) {
//            HandOverShiftDTO o1 = handOverShiftDTOList.get(i);
//            HandOverShiftDTO o2 = handOverShiftDTOSListResult.get(i);
//            assertEquals(o1, o2);
//        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        Long createDate = 16094340000000L;
        Long closedTime = 16094440000000L;
        PumpShiftDTO mockResult = PumpShiftDTO.builder()
                .id(1)
                .shift(ShiftDTO.builder().id(1).station(StationDTO.builder().id(1).build()).build())
                .pump(PumpDTO.builder().id(1).build())
                .executor((UserDTO.builder().id(3).name("EMPLOYEE").build()))
                .closedTime(closedTime)
                .createdDate(createDate).build();
        Mockito.when(handOverShiftService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, pumpShiftController.getOne(1));
    }

//    @Test
//    void create() throws CustomNotFoundException, CustomDuplicateFieldException {
//        Long createDate = 16094340000000L;
//        Long closedTime = 16094440000000L;
//        HandOverShiftDTO mockResult = HandOverShiftDTO.builder()
//                .id(1)
//                .shift(ShiftDTO.builder().id(1).station(StationDTO.builder().id(1).build()).build())
//                .pump(PumpDTO.builder().id(1).build())
//                .actor(UserDTO.builder().id(3).name("EMPLOYEE").build())
//                .closedTime(closedTime)
//                .createdDate(createDate).build();
//        HandOverShiftDTOCreate handOverShiftDTOCreate = HandOverShiftDTOCreate.builder()
//                .shiftId(1)
//                .pumpId(1)
//                .build();
//        Mockito.when(handOverShiftService.create(handOverShiftDTOCreate)).thenReturn(mockResult);
//        assertEquals(mockResult, handOverShiftController.create(handOverShiftDTOCreate));
//    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long createDate = 16094340000000L;
        Long closedTime = 16094440000000L;
        PumpShiftDTO mockResult = PumpShiftDTO.builder()
                .id(1)
                .shift(ShiftDTO.builder().id(1).station(StationDTO.builder().id(1).build()).build())
                .pump(PumpDTO.builder().id(1).build())
                .executor((UserDTO.builder().id(3).name("EMPLOYEE").build()))
                .closedTime(closedTime)
                .createdDate(createDate).build();

        Mockito.when(handOverShiftService.update(1)).thenReturn(mockResult);
        assertEquals(mockResult, pumpShiftController.update(1));
    }

    @Test
    void updateAllByStationId() throws CustomNotFoundException {
        pumpShiftController.updateAllByStationId(1);
    }
}