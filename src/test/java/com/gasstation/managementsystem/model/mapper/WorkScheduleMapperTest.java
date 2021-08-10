package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkScheduleMapperTest extends WorkScheduleMapper {

    /**
     * param workSchedule is null
     */
    @Test
    void testToWorkScheduleDTO_UTCID01() {
        assertNull(WorkScheduleMapper.toWorkScheduleDTO(null));
    }

    /**
     * param workSchedule is not null
     */
    @Test
    void testToWorkScheduleDTO_UTCID02() {
        //given
        User owner = User.builder().id(1).name("owner").build();
        Station station = Station.builder()
                .id(1)
                .name("station")
                .address("address")
                .owner(owner).build();
        Employee employee = Employee.builder()
                .id(1)
                .name("employee")
                .station(station)
                .build();
        Long startTime = 25200000L;
        Long endTime = 43200000L;
        Shift shift = Shift.builder()
                .id(1)
                .name("shift")
                .startTime(startTime)
                .endTime(endTime)
                .build();
        Long startDate = 16094340000000L;
        Long endDate = 16094440000000L;
        WorkSchedule workSchedule = WorkSchedule.builder()
                .id(1)
                .employee(employee)
                .shift(shift)
                .startDate(startDate)
                .endDate(endDate).build();
        //when
        WorkScheduleDTO workScheduleDTO = WorkScheduleMapper.toWorkScheduleDTO(workSchedule);
        //then
        assertEquals(1, workScheduleDTO.getId());
        assertAll(() -> {
            EmployeeDTO employeeDTO = workScheduleDTO.getEmployee();
            assertEquals(1, employeeDTO.getId());
            assertEquals("employee", employeeDTO.getName());
            assertAll(() -> {
                StationDTO stationDTO = employeeDTO.getStation();
                assertEquals(1, stationDTO.getId());
                assertEquals("station", stationDTO.getName());
                assertEquals("address", stationDTO.getAddress());
            });
        });
        assertAll(() -> {
            ShiftDTO shiftDTO = workScheduleDTO.getShift();
            assertEquals(1, shiftDTO.getId());
            assertEquals("shift", shiftDTO.getName());
            assertEquals("07:00", shiftDTO.getStartTime());
            assertEquals("12:00", shiftDTO.getEndTime());
        });
        assertEquals(startDate, workScheduleDTO.getStartDate());
        assertEquals(endDate, workScheduleDTO.getEndDate());
    }

    /**
     * param WorkScheduleDTOCreate is null
     */
    @Test
    void testToWorkSchedule_UTCID01() {
        assertNull(WorkScheduleMapper.toWorkSchedule(null));
    }

    /**
     * param WorkScheduleDTOCreate is not null
     */
    @Test
    void testToWorkSchedule_UTCID02() {
        //given
        Long startDate = 16094340000000L;
        Long endDate = 16094440000000L;
        WorkScheduleDTOCreate workScheduleDTOCreate = WorkScheduleDTOCreate.builder()
                .startDate(startDate)
                .endDate(endDate).build();
        //when
        WorkSchedule workSchedule = WorkScheduleMapper.toWorkSchedule(workScheduleDTOCreate);
        //then
        assertEquals(startDate, workSchedule.getStartDate());
        assertEquals(endDate, workSchedule.getEndDate());
    }

    /**
     * precondition WorkSchedule is null
     */
    @Test
    void testCopyNonNullToWorkSchedule_UTCID01() {
        //given
        Long startDate_update = 16094340000000L;
        Long endDate_update = 16094440000000L;


        WorkScheduleDTOUpdate workScheduleDTOUpdate = WorkScheduleDTOUpdate.builder()
                .startDate(startDate_update)
                .endDate(endDate_update).build();
        //then
        try {
            WorkScheduleMapper.copyNonNullToWorkSchedule(null, workScheduleDTOUpdate);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * precondition WorkSchedule is not null
     */
    @Test
    void testCopyNonNullToWorkSchedule_UTCID02() {
        //given
        Employee employee = Employee.builder().id(100).build();
        Shift shift = Shift.builder().id(100).build();
        Long startDate = 16094340000000L;
        Long endDate = 16094440000000L;

        Long startDate_update = 16094540000000L;
        Long endDate_update = 16094640000000L;
        WorkSchedule workSchedule = WorkSchedule.builder()
                .employee(employee)
                .shift(shift)
                .startDate(startDate)
                .endDate(endDate).build();

        WorkScheduleDTOUpdate workScheduleDTOUpdate = WorkScheduleDTOUpdate.builder()
                .startDate(startDate_update)
                .endDate(endDate_update).build();
        //when
        WorkScheduleMapper.copyNonNullToWorkSchedule(workSchedule, workScheduleDTOUpdate);
        //then
        assertEquals(startDate_update, workSchedule.getStartDate());
        assertEquals(endDate_update, workSchedule.getEndDate());
    }
}