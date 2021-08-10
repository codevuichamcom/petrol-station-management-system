package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTO;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOCreate;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOFilter;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOUpdate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.service.impl.ExpenseServiceImpl;
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

class ExpenseControllerTest {
    @Mock
    ExpenseServiceImpl expenseService;

    @InjectMocks
    ExpenseController expenseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param pageSize is not null
     */
    @Test
    void getAll_UTCID01() {
        Long creatDate = 16094340000000L;
        List<ExpenseDTO> expenseDTOList = IntStream.range(1, 10).mapToObj(i -> ExpenseDTO.builder()
                .id(i)
                .reason("reason" + i)
                .amount(100d)
                .createdDate(creatDate)
                .station(StationDTO.builder()
                        .id(i)
                        .name("station" + i)
                        .build())
                .fuelImport(FuelImportDTO.builder()
                        .id(i)
                        .name("fuelImport" + i)
                        .build())
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", expenseDTOList);

        final int PAGE_INDEX = 1;
        final int PAGE_SIZE = 3;
        Integer[] shiftIds = new Integer[1];
        shiftIds[0] = 1;
        Integer[] pumpIds = new Integer[1];
        pumpIds[0] = 1;
        Integer[] stationIds = new Integer[1];
        stationIds[0] = 1;
        String[] statuses = new String[1];
        statuses[0] = "status1";
        ExpenseDTOFilter filter = ExpenseDTOFilter.builder()
                .pageIndex(PAGE_INDEX)
                .pageSize(PAGE_SIZE)
                .reason("reason")
                .amount(200d)
                .createdDate(creatDate)
                .createdDate(16094340000000L)
                .stationIds(stationIds)
                .creatorName("creatorName")
                .build();
        Mockito.when(expenseService.findAll(filter)).thenReturn(map);

        HashMap<String, Object> mapResult = expenseController.getAll(PAGE_INDEX, PAGE_SIZE,
                "reason", 200d, creatDate, stationIds, "creatorName");
//        Mockito.when(expenseService.findAll(PageRequest.of(PAGE_INDEX - 1, PAGE_SIZE, Sort.Direction.DESC, "id"))).thenReturn(map);
//        HashMap<String, Object> mapResult = expenseController.getAll(PAGE_INDEX, PAGE_SIZE);

//        assertTrue(mapResult.containsKey("data"));
//        List<ExpenseDTO> expenseDTOListResult = (List<ExpenseDTO>) mapResult.get("data");
//        assertEquals(expenseDTOList.size(), expenseDTOListResult.size());
//        for (int i = 0; i < expenseDTOListResult.size(); i++) {
//            ExpenseDTO o1 = expenseDTOList.get(i);
//            ExpenseDTO o2 = expenseDTOListResult.get(i);
//            assertEquals(o1, o2);
//        }
    }

    /**
     * param pageSize is null
     */
    @Test
    void getAll_UTCID02() {
        Long creatDate = 16094340000000L;
        List<ExpenseDTO> expenseDTOList = IntStream.range(1, 10).mapToObj(i -> ExpenseDTO.builder()
                .id(i)
                .reason("reason" + i)
                .amount(100d)
                .createdDate(creatDate)
                .station(StationDTO.builder()
                        .id(i)
                        .name("station" + i)
                        .build())
                .fuelImport(FuelImportDTO.builder()
                        .id(i)
                        .name("fuelImport" + i)
                        .build())
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", expenseDTOList);
        final int PAGE_INDEX = 1;
        final int PAGE_SIZE = 3;
//        Mockito.when(expenseService.findAll(Sort.by(Sort.Direction.DESC, "id"))).thenReturn(map);
//        HashMap<String, Object> mapResult = expenseController.getAll(PAGE_INDEX, null);
//        assertTrue(mapResult.containsKey("data"));
//        List<ExpenseDTO> expenseDTOListResult = (List<ExpenseDTO>) mapResult.get("data");
//        assertEquals(expenseDTOList.size(), expenseDTOListResult.size());
//        for (int i = 0; i < expenseDTOListResult.size(); i++) {
//            ExpenseDTO o1 = expenseDTOList.get(i);
//            ExpenseDTO o2 = expenseDTOListResult.get(i);
//            assertEquals(o1, o2);
//        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        Long creatDate = 16094340000000L;
        ExpenseDTO mockResult = ExpenseDTO.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .createdDate(creatDate)
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImportDTO.builder()
                        .id(1)
                        .name("fuelImport")
                        .build())
                .build();
        Mockito.when(expenseService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, expenseController.getOne(1));
    }

    @Test
    void create() throws CustomNotFoundException {
        Long creatDate = 16094340000000L;
        Long creatDateUpdate = 16094440000000L;
        ExpenseDTO mockResult = ExpenseDTO.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .createdDate(creatDate)
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImportDTO.builder()
                        .id(1)
                        .name("fuelImport")
                        .build())
                .build();
        ExpenseDTOCreate expenseDTOCreate = ExpenseDTOCreate.builder()
                .reason("reason")
                .amount(100d)
                .stationId(1)
                .fuelImportId(1)
                .build();
        Mockito.when(expenseService.create(expenseDTOCreate)).thenReturn(mockResult);
        assertEquals(mockResult, expenseController.create(expenseDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException {
        Long creatDate = 16094340000000L;
        Long creatDateUpdate = 16094340000000L;
        ExpenseDTO mockResult = ExpenseDTO.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .createdDate(creatDate)
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImportDTO.builder()
                        .id(1)
                        .name("fuelImport")
                        .build())
                .build();
        ExpenseDTOUpdate expenseDTOUpdate = ExpenseDTOUpdate.builder()
                .reason("reason")
                .amount(100d)
                .stationId(1)
                .fuelImportId(1)
                .build();
        Mockito.when(expenseService.update(1, expenseDTOUpdate)).thenReturn(mockResult);
        assertEquals(mockResult, expenseController.update(1, expenseDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        Long creatDate = 16094340000000L;
        ExpenseDTO mockResult = ExpenseDTO.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .createdDate(creatDate)
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImportDTO.builder()
                        .id(1)
                        .name("fuelImport")
                        .build())
                .build();
        Mockito.when(expenseService.delete(1)).thenReturn(mockResult);
        assertEquals(mockResult, expenseController.delete(1));
    }
}