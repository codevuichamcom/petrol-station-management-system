//package com.gasstation.managementsystem.controller;
//
//import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
//import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
//import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
//import com.gasstation.managementsystem.model.dto.station.StationDTO;
//import com.gasstation.managementsystem.model.dto.user.UserDTO;
//import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
//import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
//import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
//import com.gasstation.managementsystem.service.impl.WorkScheduleServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class WorkScheduleControllerTest {
//    @Mock
//    WorkScheduleServiceImpl workScheduleService;
//
//    @InjectMocks
//    WorkScheduleController workScheduleController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    /**
//     * param pageSize is not null
//     */
//    @Test
//    void getAll_UTCID01() {
//        //given
//        UserDTO ownerDTO = UserDTO.builder().id(1).name("owner").build();
//        StationDTO stationDTO = StationDTO.builder()
//                .id(1)
//                .name("station")
//                .address("address")
//                .owner(ownerDTO).build();
//        EmployeeDTO employeeDTO = EmployeeDTO.builder()
//                .id(1)
//                .name("employee")
//                .station(stationDTO)
//                .build();
//        ShiftDTO shiftDTO = ShiftDTO.builder()
//                .id(1)
//                .name("shift")
//                .startTime("2021-01-01")
//                .endTime("2021-01-31")
//                .build();
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        List<WorkScheduleDTO> workScheduleDTOList = IntStream.range(1, 10).mapToObj(i -> WorkScheduleDTO.builder()
//                .id(i)
//                .employee(employeeDTO)
//                .shift(shiftDTO)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build()).collect(Collectors.toList());
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("data", workScheduleDTOList);
//        final int PAGE_INDEX = 1;
//        final int PAGE_SIZE = 3;
//        Mockito.when(workScheduleService.findAll(PageRequest.of(PAGE_INDEX - 1, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "id")))).thenReturn(map);
//        HashMap<String, Object> mapResult = workScheduleController.getAll(PAGE_INDEX, PAGE_SIZE);
//
//        assertTrue(mapResult.containsKey("data"));
//        List<WorkScheduleDTO> workScheduleDTOSListResult = (List<WorkScheduleDTO>) mapResult.get("data");
//        assertEquals(workScheduleDTOList.size(), workScheduleDTOSListResult.size());
//        for (int i = 0; i < workScheduleDTOSListResult.size(); i++) {
//            WorkScheduleDTO o1 = workScheduleDTOList.get(i);
//            WorkScheduleDTO o2 = workScheduleDTOSListResult.get(i);
//            assertEquals(o1, o2);
//        }
//    }
//
//    /**
//     * param pageSize is null
//     */
//    @Test
//    void getAll_UTCID02() {
//        //given
//        UserDTO ownerDTO = UserDTO.builder().id(1).name("owner").build();
//        StationDTO stationDTO = StationDTO.builder()
//                .id(1)
//                .name("station")
//                .address("address")
//                .owner(ownerDTO).build();
//        EmployeeDTO employeeDTO = EmployeeDTO.builder()
//                .id(1)
//                .name("employee")
//                .station(stationDTO)
//                .build();
//        ShiftDTO shiftDTO = ShiftDTO.builder()
//                .id(1)
//                .name("shift")
//                .startTime("2021-01-01")
//                .endTime("2021-01-31")
//                .build();
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        List<WorkScheduleDTO> workScheduleDTOList = IntStream.range(1, 10).mapToObj(i -> WorkScheduleDTO.builder()
//                .id(i)
//                .employee(employeeDTO)
//                .shift(shiftDTO)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build()).collect(Collectors.toList());
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("data", workScheduleDTOList);
//        final int PAGE_INDEX = 1;
//        Mockito.when(workScheduleService.findAll(Sort.by(Sort.Direction.DESC, "id"))).thenReturn(map);
//        HashMap<String, Object> mapResult = workScheduleController.getAll(PAGE_INDEX, null);
//
//        assertTrue(mapResult.containsKey("data"));
//        List<WorkScheduleDTO> workScheduleDTOSListResult = (List<WorkScheduleDTO>) mapResult.get("data");
//        assertEquals(workScheduleDTOList.size(), workScheduleDTOSListResult.size());
//        for (int i = 0; i < workScheduleDTOSListResult.size(); i++) {
//            WorkScheduleDTO o1 = workScheduleDTOList.get(i);
//            WorkScheduleDTO o2 = workScheduleDTOSListResult.get(i);
//            assertEquals(o1, o2);
//        }
//    }
//
//    @Test
//    void getOne() throws CustomNotFoundException {
//        //given
//        UserDTO ownerDTO = UserDTO.builder().id(1).name("owner").build();
//        StationDTO stationDTO = StationDTO.builder()
//                .id(1)
//                .name("station")
//                .address("address")
//                .owner(ownerDTO).build();
//        EmployeeDTO employeeDTO = EmployeeDTO.builder()
//                .id(1)
//                .name("employee")
//                .station(stationDTO)
//                .build();
//        ShiftDTO shiftDTO = ShiftDTO.builder()
//                .id(1)
//                .name("shift")
//                .startTime("2021-01-01")
//                .endTime("2021-01-31")
//                .build();
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        WorkScheduleDTO mockResult = WorkScheduleDTO.builder()
//                .id(1)
//                .employee(employeeDTO)
//                .shift(shiftDTO)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
//        Mockito.when(workScheduleService.findById(1)).thenReturn(mockResult);
//        assertEquals(mockResult, workScheduleController.getOne(1));
//    }
//
//    @Test
//    void create() throws CustomNotFoundException {
//        //given
//        UserDTO ownerDTO = UserDTO.builder().id(1).name("owner").build();
//        StationDTO stationDTO = StationDTO.builder()
//                .id(1)
//                .name("station")
//                .address("address")
//                .owner(ownerDTO).build();
//        EmployeeDTO employeeDTO = EmployeeDTO.builder()
//                .id(1)
//                .name("employee")
//                .station(stationDTO)
//                .build();
//        ShiftDTO shiftDTO = ShiftDTO.builder()
//                .id(1)
//                .name("shift")
//                .startTime("2021-01-01")
//                .endTime("2021-01-31")
//                .build();
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        WorkScheduleDTO mockResult = WorkScheduleDTO.builder()
//                .id(1)
//                .employee(employeeDTO)
//                .shift(shiftDTO)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
//        WorkScheduleDTOCreate workScheduleDTOCreate = WorkScheduleDTOCreate.builder()
//                .employeeId(1)
//                .shiftId(1)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
//        Mockito.when(workScheduleService.create(workScheduleDTOCreate)).thenReturn(mockResult);
//        assertEquals(mockResult, workScheduleController.create(workScheduleDTOCreate));
//    }
//
//    @Test
//    void update() throws CustomNotFoundException {
//        //given
//        UserDTO ownerDTO = UserDTO.builder().id(1).name("owner").build();
//        StationDTO stationDTO = StationDTO.builder()
//                .id(1)
//                .name("station")
//                .address("address")
//                .owner(ownerDTO).build();
//        ShiftDTO shiftDTO = ShiftDTO.builder()
//                .id(1)
//                .name("shift")
//                .startTime("2021-01-01")
//                .endTime("2021-01-31")
//                .build();
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        WorkScheduleDTO mockResult = WorkScheduleDTO.builder()
//                .id(1)
//                .shift(shiftDTO)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
//        WorkScheduleDTOUpdate workScheduleDTOUpdate = WorkScheduleDTOUpdate.builder()
//                .shiftId(1)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
//        Mockito.when(workScheduleService.update(1, workScheduleDTOUpdate)).thenReturn(mockResult);
//        assertEquals(mockResult, workScheduleController.update(1, workScheduleDTOUpdate));
//    }
//
//    @Test
//    void delete() throws CustomNotFoundException {
//        //given
//        UserDTO ownerDTO = UserDTO.builder().id(1).name("owner").build();
//        StationDTO stationDTO = StationDTO.builder()
//                .id(1)
//                .name("station")
//                .address("address")
//                .owner(ownerDTO).build();
//        ShiftDTO shiftDTO = ShiftDTO.builder()
//                .id(1)
//                .name("shift")
//                .startTime("2021-01-01")
//                .endTime("2021-01-31")
//                .build();
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        WorkScheduleDTO mockResult = WorkScheduleDTO.builder()
//                .id(1)
//                .shift(shiftDTO)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
//        Mockito.when(workScheduleService.delete(1)).thenReturn(mockResult);
//        assertEquals(mockResult, workScheduleController.delete(1));
//    }
//}