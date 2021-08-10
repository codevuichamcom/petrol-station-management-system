package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShiftMapperTest extends ShiftMapper {
    /**
     * param shift is null
     */
    @Test
    void testToShiftDTO_UTCID01() {
        assertNull(ShiftMapper.toShiftDTO(null));
    }

    /**
     * param shift is not null
     */
    @Test
    void testToShiftDTO_UTCID02() {
        //given
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
        //when
        ShiftDTO shiftDTO = ShiftMapper.toShiftDTO(shift);
        //then
        assertEquals(1, shiftDTO.getId());
        assertEquals("Morning", shiftDTO.getName());
        assertEquals("07:00", shiftDTO.getStartTime());
        assertEquals("12:00", shiftDTO.getEndTime());
        assertAll(() -> {
            StationDTO stationDTO = shiftDTO.getStation();
            assertEquals(1, stationDTO.getId());
            assertEquals("station", stationDTO.getName());
            assertEquals("address", stationDTO.getAddress());
            assertAll(() -> {
                UserDTO owner = stationDTO.getOwner();
                assertEquals(1, owner.getId());
                assertEquals("owner", owner.getName());
            });
        });
    }

    /**
     * param ShiftDTOCreate is null
     */
    @Test
    void testToShift_UTCID01() {
        assertNull(ShiftMapper.toShift(null));
    }

    /**
     * param ShiftDTOCreate is not null
     */
    @Test
    void testToShift_UTCID02() {
        //given
        Long startTime = 25200000L;
        Long endTime = 43200000L;

        ShiftDTOCreate shiftDTOCreate = ShiftDTOCreate.builder()
                .name("shift1")
                .startTime("07:00")
                .endTime("12:00")
                .build();
        //when
        Shift shift = ShiftMapper.toShift(shiftDTOCreate);
        //then
        assertEquals("shift1", shift.getName());
        assertEquals(startTime, shift.getStartTime());
        assertEquals(endTime, shift.getEndTime());
    }

    /**
     * param shift is not null
     */
    @Test
    void testCopyNonNullToShift_UTCID02() {
        //given
        Long startTime = 25200000L;
        Long endTime = 43200000L;
        Shift shift = Shift.builder()
                .id(1)
                .name("Morling")
                .startTime(startTime)
                .endTime(endTime)
                .build();

        ShiftDTOUpdate shiftDTOUpdate = ShiftDTOUpdate.builder()
                .name("Morning")
                .startTime("08:00")
                .endTime("13:00")
                .build();
        //when
        ShiftMapper.copyNonNullToShift(shift, shiftDTOUpdate);
        //then
        assertEquals("Morning", shift.getName());
        assertEquals(28800000, shift.getStartTime());
        assertEquals(46800000, shift.getEndTime());
    }
}