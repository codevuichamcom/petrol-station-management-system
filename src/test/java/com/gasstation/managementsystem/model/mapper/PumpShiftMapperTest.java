package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PumpShiftMapperTest {

    @Test
    void toHandOverShiftDTO() {
        Long createDate = 16094340000000L;
        Long closedTime = 16094440000000L;
        Long startTime = 25200000L;
        Long endTime = 43200000L;
        PumpShift mockResult = PumpShift.builder()
                .id(1)
                .shift(Shift.builder().id(1)
                        .startTime(startTime)
                        .endTime(endTime)
                        .station(Station.builder().id(1).build()).build())
                .pump(Pump.builder().id(1).build())
                .executor((User.builder().id(3).name("EMPLOYEE").build()))
                .closedTime(closedTime)
                .createdDate(createDate).build();
        PumpShiftDTO pumpShiftDTO = PumpShiftMapper.toHandOverShiftDTO(mockResult);
        assertEquals(mockResult.getId(), pumpShiftDTO.getId());
        assertEquals(mockResult.getShift().getId(), pumpShiftDTO.getShift().getId());
        assertEquals(mockResult.getPump().getId(), pumpShiftDTO.getPump().getId());
        assertEquals(mockResult.getExecutor().getId(), pumpShiftDTO.getExecutor().getId());
        assertEquals(mockResult.getClosedTime(), pumpShiftDTO.getClosedTime());
        assertEquals(mockResult.getCreatedDate(), pumpShiftDTO.getCreatedDate());
    }
}