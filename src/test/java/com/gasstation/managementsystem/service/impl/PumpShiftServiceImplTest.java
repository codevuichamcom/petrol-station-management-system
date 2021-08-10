package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTO;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTOFilter;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.mapper.PumpShiftMapper;
import com.gasstation.managementsystem.repository.PumpShiftRepository;
import com.gasstation.managementsystem.repository.criteria.PumpShiftRepositoryCriteria;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PumpShiftServiceImplTest {
    @Mock
    private EntityManager em;

    @Mock
    private PumpShiftRepository pumpShiftRepository;

    @Mock
    private PumpShiftRepositoryCriteria handOverShiftCriteria;

    @Mock
    private OptionalValidate optionalValidate;

    @Mock
    private UserHelper userHelper;

    @InjectMocks
    private PumpShiftServiceImpl handOverShiftService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<PumpShift> mockRepository = new ArrayList<>();
        List<PumpShiftDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);

        Long createDate = 16094340000000L;
        Long closedTime = 16094440000000L;
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

        HashMap<String, Object> temp = new HashMap<>();
        temp.put("totalElement",1);
        temp.put("data",mockRepository);
        temp.put("totalPage",3);
        Mockito.when(pumpShiftRepository.findAll()).thenReturn(mockRepository);
        Mockito.when(handOverShiftCriteria.findAll(Mockito.any(PumpShiftDTOFilter.class))).thenReturn(temp);
        List<PumpShiftDTO> listResultService = (List<PumpShiftDTO>)
                handOverShiftService.findAll(filter).get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            PumpShiftDTO o1 = mockResult.get(i);
            PumpShiftDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<PumpShift> mockRepository, List<PumpShiftDTO> mockResult) {
        for (int i = 1; i <= 10; i++) {
            Long createDate = 16094240000000L;
            Long closedTime = 16094540000000L;
            PumpShift pumpShift = PumpShift.builder()
                    .id(i)
                    .shift(Shift.builder().id(1).station(Station.builder().id(1).build()).build())
                    .pump(Pump.builder().id(1).build())
                    .executor((User.builder().id(3).name("EMPLOYEE").build()))
                    .closedTime(closedTime)
                    .createdDate(createDate).build();
            mockRepository.add(pumpShift);
            mockResult.add(PumpShiftMapper.toHandOverShiftDTO(pumpShift));
        }
    }

    @Test
    void findById() throws CustomNotFoundException {
        Long createDate = 16094340000000L;
        Long closedTime = 16094440000000L;
        PumpShift pumpShift = PumpShift.builder()
                .id(1)
                .shift(Shift.builder().id(1).build())
                .pump(Pump.builder().id(1).build())
                .executor((User.builder().id(3).name("EMPLOYEE").build()))
                .closedTime(closedTime)
                .createdDate(createDate).build();
        PumpShiftDTO mockResult = PumpShiftDTO.builder()
                .id(1)
                .shift(ShiftDTO.builder().id(1).startTime("00:00")
                        .endTime("00:00")
                        .station(StationDTO.builder().id(1).build()).build())
                .pump(PumpDTO.builder().id(1).build())
                .executor((UserDTO.builder().id(3).name("EMPLOYEE").build()))
                .closedTime(closedTime)
                .createdDate(createDate)
                .build();
        Mockito.when(optionalValidate.getPumpShiftById(1)).thenReturn(pumpShift);
        assertEquals(mockResult, handOverShiftService.findById(1));
    }

    @Test
    void update() throws CustomNotFoundException {
        Long createDate = 16094340000000L;
        PumpShift pumpShift = PumpShift.builder()
                .id(1)
                .shift(Shift.builder().id(1).build())
                .pump(Pump.builder().id(1).build())
                .closedTime(DateTimeHelper.getCurrentUnixTime())
                .createdDate(createDate).build();
        PumpShiftDTO mockResult = PumpShiftDTO.builder()
                .id(1)
                .shift(ShiftDTO.builder().id(1).startTime("00:00")
                        .endTime("00:00")
                        .build())
                .pump(PumpDTO.builder().id(1).build())
                .closedTime(DateTimeHelper.getCurrentUnixTime())
                .createdDate(createDate)
                .build();
        User user = User.builder().id(1).name("OWNER").build();
        Mockito.when(userHelper.getUserLogin()).thenReturn(user);
        Mockito.when(optionalValidate.getPumpShiftById(1)).thenReturn(pumpShift);
        assertEquals(mockResult.getShift(), handOverShiftService.update(1).getShift());
    }

    @Test
    void updateAllByStationId() throws CustomNotFoundException {
        Long createDate = 16094340000000L;
        Station station = Station.builder().id(1).build();
        PumpShift pumpShift = PumpShift.builder()
                .id(1)
                .createdDate(createDate)
                .build();
        PumpShiftDTO mockResult = PumpShiftDTO.builder()
                .id(1)
                .createdDate(createDate)
                .build();
        List<PumpShift> pumpShiftList = new ArrayList<>();
        pumpShiftList.add(pumpShift);
//     User user = User.builder().id(1).name("OWNER").build();
//    Mockito.when(userHelper.getUserLogin()).thenReturn(user);
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(station);
        Mockito.when(pumpShiftRepository.save(Mockito.any(PumpShift.class))).thenReturn(pumpShift);
        Mockito.when(pumpShiftRepository.findAllByStationId(1)).thenReturn(pumpShiftList);
        handOverShiftService.updateAllByStationId(1);
//        assertEquals(mockResult, handOverShiftService.updateAllByStationId(1).get("data"));
    }
}