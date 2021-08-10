//package com.gasstation.managementsystem.service.impl;
//
//import com.gasstation.managementsystem.entity.*;
//import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
//import com.gasstation.managementsystem.model.dto.employee.EmployeeDTO;
//import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
//import com.gasstation.managementsystem.model.dto.station.StationDTO;
//import com.gasstation.managementsystem.model.dto.user.UserDTO;
//import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
//import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
//import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
//import com.gasstation.managementsystem.model.mapper.WorkScheduleMapper;
//import com.gasstation.managementsystem.repository.WorkScheduleRepository;
//import com.gasstation.managementsystem.utils.OptionalValidate;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class WorkScheduleServiceImplTest {
//    @Mock
//    private WorkScheduleRepository workScheduleRepository;
//
//    @Mock
//    private OptionalValidate optionalValidate;
//
//    @InjectMocks
//    private WorkScheduleServiceImpl workScheduleService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    /**
//     * param sort
//     */
//    @Test
//    void findAll_UTCID01() {
//        List<WorkSchedule> mockRepository = new ArrayList<>();
//        List<WorkScheduleDTO> mockResult = new ArrayList<>();
//        mockData(mockRepository, mockResult);
//
//        //giả lập
//        Mockito.when(workScheduleRepository.findAll()).thenReturn(mockRepository);
//        Sort sort = null;
//        List<WorkScheduleDTO> listResultService = (List<WorkScheduleDTO>) workScheduleService.findAll(sort).get("data");
//        for (int i = 0; i < listResultService.size(); i++) {
//            WorkScheduleDTO o1 = mockResult.get(i);
//            WorkScheduleDTO o2 = listResultService.get(i);
//            assertEquals(o1, o2);
//        }
//    }
//
//    private void mockData(List<WorkSchedule> mockRepository, List<WorkScheduleDTO> mockResult) {
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        for (int i = 1; i <= 10; i++) {
//            Employee employee = new Employee();
//            employee.setId(i);
//            employee.setStation(Station.builder().id(1).owner(User.builder().id(2).build()).build());
//            Shift shift = new Shift();
//            shift.setId(i);
//            shift.setStation(Station.builder().id(1).owner(User.builder().id(2).build()).build());
//            WorkSchedule workSchedule = WorkSchedule.builder().
//                    employee(employee).shift(shift).
//                    id(1).startDate(startDate).endDate(endDate).build();
//            mockRepository.add(workSchedule);
//            mockResult.add(WorkScheduleMapper.toWorkScheduleDTO(workSchedule));
//        }
//    }
//
//    /**
//     * param pageable
//     */
//    @Test
//    void findAll_UTCID02() {
//        List<WorkSchedule> mockRepository = new ArrayList<>();
//        List<WorkScheduleDTO> mockResult = new ArrayList<>();
//        mockData(mockRepository, mockResult);
//
//        //giả lập
//        Mockito.when(workScheduleRepository.findAll()).thenReturn(mockRepository);
//        final int PAGE_INDEX = 1;
//        final int PAGE_SIZE = 3;
//        Page<WorkSchedule> mockRepositoryPaged = new PageImpl<>(mockRepository.subList(PAGE_INDEX - 1, PAGE_SIZE));
//        List<WorkScheduleDTO> mockResultPaged = mockResult.subList(PAGE_INDEX - 1, PAGE_SIZE);
//        Mockito.when(workScheduleRepository.findAll(PageRequest.of(PAGE_INDEX - 1, PAGE_SIZE))).thenReturn(mockRepositoryPaged);
//        List<WorkScheduleDTO> listResultService = (List<WorkScheduleDTO>) workScheduleService.findAll(PageRequest.of(PAGE_INDEX - 1, PAGE_SIZE)).get("data");
//        for (int i = 0; i < listResultService.size(); i++) {
//            WorkScheduleDTO o1 = mockResultPaged.get(i);
//            WorkScheduleDTO o2 = listResultService.get(i);
//            assertEquals(o1, o2);
//        }
//    }
//
//    @Test
//    void findById() throws CustomNotFoundException {
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        Employee employee = Employee.builder().id(1)
//                .station(Station.builder().id(1).owner(User.builder().id(2).build()).build())
//                .build();
//        EmployeeDTO employeeDTO = EmployeeDTO.builder().id(1)
//                .station(StationDTO.builder().id(1).owner(UserDTO.builder().id(2).build()).build())
//                .build();
//        WorkSchedule mockRepository = WorkSchedule.builder().
//                id(1).
//                employee(employee).
//                startDate(startDate).endDate(endDate).build();
//        WorkScheduleDTO mockResult = WorkScheduleDTO.builder().
//                id(1).
//                employee(employeeDTO).
//                startDate(startDate).endDate(endDate).build();
//        Mockito.when(optionalValidate.getWorkScheduleById(1)).thenReturn(mockRepository);
//        assertEquals(mockResult, workScheduleService.findById(1));
//    }
//
//    @Test
//    void create() throws CustomNotFoundException {
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        Shift shift = Shift.builder().id(1).build();
//        ShiftDTO shiftDTO = ShiftDTO.builder().id(1).startTime("00:00").endTime("00:00").build();
//        Employee employee = Employee.builder().id(1)
//                .station(Station.builder().id(1).owner(User.builder().id(2).build()).build())
//                .build();
//        EmployeeDTO employeeDTO = EmployeeDTO.builder().id(1)
//                .station(StationDTO.builder().id(1).owner(UserDTO.builder().id(2).build()).build())
//                .build();
//        WorkSchedule mockRepository = WorkSchedule.builder().
//                id(1).
//                shift(shift).
//                employee(employee).
//                startDate(startDate).endDate(endDate).build();
//        WorkScheduleDTO mockResult = WorkScheduleDTO.builder().
//                id(1).
//                employee(employeeDTO).
//                shift(shiftDTO).
//                startDate(startDate).endDate(endDate).build();
//        WorkScheduleDTOCreate workScheduleDTOCreate = WorkScheduleDTOCreate.builder().
//                employeeId(1).
//                shiftId(1).
//                startDate(startDate).endDate(endDate).build();
//        Mockito.when(optionalValidate.getEmployeeById(1)).thenReturn(employee);
//        Mockito.when(optionalValidate.getShiftById(1)).thenReturn(shift);
//        Mockito.when(workScheduleRepository.save(Mockito.any(WorkSchedule.class))).thenReturn(mockRepository);
//        assertEquals(mockResult, workScheduleService.create(workScheduleDTOCreate));
//    }
//
//    @Test
//    void update() throws CustomNotFoundException {
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        Shift shift = Shift.builder().id(1).build();
//        ShiftDTO shiftDTO = ShiftDTO.builder().id(1).startTime("00:00").endTime("00:00").build();
//        Employee employee = Employee.builder().id(1)
//                .station(Station.builder().id(1).owner(User.builder().id(2).build()).build())
//                .build();
//        EmployeeDTO employeeDTO = EmployeeDTO.builder().id(1)
//                .station(StationDTO.builder().id(1).owner(UserDTO.builder().id(2).build()).build())
//                .build();
//        WorkSchedule mockRepository = WorkSchedule.builder().
//                id(1).
//                shift(shift).
//                employee(employee).
//                startDate(startDate).endDate(endDate).build();
//        WorkScheduleDTO mockResult = WorkScheduleDTO.builder().
//                id(1).
//                employee(employeeDTO).
//                shift(shiftDTO).
//                startDate(startDate).endDate(endDate).build();
//        WorkScheduleDTOUpdate workScheduleDTOUpdate = WorkScheduleDTOUpdate.builder().
//                shiftId(1).
//                startDate(startDate).endDate(endDate).build();
//
//        Mockito.when(workScheduleRepository.save(Mockito.any(WorkSchedule.class))).thenReturn(mockRepository);
//        Mockito.when(optionalValidate.getWorkScheduleById(1)).thenReturn(mockRepository);
//        Mockito.when(optionalValidate.getShiftById(1)).thenReturn(shift);
//        assertEquals(mockResult, workScheduleService.update(1, workScheduleDTOUpdate));
//    }
//    @Test
//    void delete() throws CustomNotFoundException {
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        Shift shift = Shift.builder().id(1).build();
//        ShiftDTO shiftDTO = ShiftDTO.builder().id(1).startTime("00:00").endTime("00:00").build();
//        Employee employee = Employee.builder().id(1)
//                .station(Station.builder().id(1).owner(User.builder().id(2).build()).build())
//                .build();
//        EmployeeDTO employeeDTO = EmployeeDTO.builder().id(1)
//                .station(StationDTO.builder().id(1).owner(UserDTO.builder().id(2).build()).build())
//                .build();
//        WorkSchedule mockRepository = WorkSchedule.builder().
//                id(1).
//                shift(shift).
//                employee(employee).
//                startDate(startDate).endDate(endDate).build();
//        WorkScheduleDTO mockResult = WorkScheduleDTO.builder().
//                id(1).
//                employee(employeeDTO).
//                shift(shiftDTO).
//                startDate(startDate).endDate(endDate).build();
//        Mockito.when(optionalValidate.getWorkScheduleById(1)).thenReturn(mockRepository);
//        assertEquals(mockResult, workScheduleService.delete(1));
//    }
//}