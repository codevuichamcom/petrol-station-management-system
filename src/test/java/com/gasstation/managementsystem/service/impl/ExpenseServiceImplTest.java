package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTO;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOCreate;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOFilter;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOUpdate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.mapper.ExpenseMapper;
import com.gasstation.managementsystem.repository.ExpenseRepository;
import com.gasstation.managementsystem.repository.criteria.ExpenseRepositoryCriteria;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseServiceImplTest {
    @Mock
    private ExpenseRepository expenseRepository;
    @Mock
    private OptionalValidate optionalValidate;
    @Mock
    private UserHelper userHelper;
    @Mock
    private ExpenseRepositoryCriteria expenseCriteria;
    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Expense> mockRepository = new ArrayList<>();
        List<ExpenseDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);

        ExpenseDTOFilter filter = ExpenseDTOFilter.builder().amount(100d).build();
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("totalElement", 10);
        temp.put("data", mockRepository);
        temp.put("totalPage", 3);
        Mockito.when(expenseCriteria.findAll(Mockito.any(ExpenseDTOFilter.class))).thenReturn(temp);
        List<ExpenseDTO> listResultService = (List<ExpenseDTO>) expenseService.findAll(filter).get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            ExpenseDTO o1 = mockResult.get(i);
            ExpenseDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<Expense> mockRepository, List<ExpenseDTO> mockResult) {
        for (int i = 1; i <= 10; i++) {
            Expense expense = Expense.builder()
                    .id(1)
                    .reason("reason")
                    .amount(100d)

                    .station(Station.builder()
                            .id(1)
                            .name("station")
                            .build())
                    .fuelImport(FuelImport.builder()
                            .id(1)
                            .name("fuelImport")
                            .build())
                    .build();
            mockRepository.add(expense);
            mockResult.add(ExpenseMapper.toExpenseDTO(expense));
        }
    }

    @Test
    void findById() throws CustomNotFoundException {
        Expense mockRepository = Expense.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .station(Station.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImport.builder()
                        .id(1)
                        .name("fuelImport")
                        .build())
                .build();
        ExpenseDTO mockResult = ExpenseDTO.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImportDTO.builder()
                        .id(1)
                        .name("fuelImport")
                        .createdDate(0L)
                        .build())
                .build();
        Mockito.when(optionalValidate.getExpenseById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, expenseService.findById(1));
    }

    @Test
    void create() throws CustomNotFoundException {
        Station station = Station.builder()
                .id(1)
                .name("station")
                .build();
        FuelImport fuelImport = FuelImport.builder()
                .id(1)
                .name("fuelImport")
                .build();
        Expense mockRepository = Expense.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .station(station)
                .fuelImport(fuelImport)
                .build();
        ExpenseDTO mockResult = ExpenseDTO.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImportDTO.builder()
                        .id(1)
                        .name("fuelImport")
                        .createdDate(0L)
                        .build())
                .build();
        ExpenseDTOCreate expenseDTOCreate = ExpenseDTOCreate.builder()
                .reason("reason")
                .amount(100d)
                .stationId(1)
                .fuelImportId(1)
                .build();
        User user = User.builder().id(2).name("OWNER").build();
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(station);
        Mockito.when(optionalValidate.getFuelImportById(1)).thenReturn(fuelImport);
        Mockito.when(userHelper.getUserLogin()).thenReturn(user);
        Mockito.when(expenseRepository.save(Mockito.any(Expense.class))).thenReturn(mockRepository);
        assertEquals(mockResult, expenseService.create(expenseDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException {
        Station station = Station.builder()
                .id(1)
                .name("station")
                .build();
        FuelImport fuelImport = FuelImport.builder()
                .id(1)
                .name("fuelImport")
                .build();
        Expense mockRepository = Expense.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .station(station)
                .fuelImport(fuelImport)
                .build();
        ExpenseDTO mockResult = ExpenseDTO.builder()
                .id(1)
                .reason("reason_update")
                .amount(200d)
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImportDTO.builder()
                        .id(1)
                        .name("fuelImport")
                        .createdDate(0L)
                        .build())
                .build();
        ExpenseDTOUpdate expenseDTOUpdate = ExpenseDTOUpdate.builder()
                .reason("reason_update")
                .amount(200d)
                .stationId(1)
                .fuelImportId(1)
                .build();
        User user = User.builder().id(2).name("OWNER").build();
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(station);
        Mockito.when(optionalValidate.getExpenseById(1)).thenReturn(mockRepository);
        Mockito.when(optionalValidate.getFuelImportById(1)).thenReturn(fuelImport);
        Mockito.when(userHelper.getUserLogin()).thenReturn(user);
        Mockito.when(expenseRepository.save(Mockito.any(Expense.class))).thenReturn(mockRepository);
        assertEquals(mockResult, expenseService.update(1,expenseDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        Station station = Station.builder()
                .id(1)
                .name("station")
                .build();
        FuelImport fuelImport = FuelImport.builder()
                .id(1)
                .name("fuelImport")
                .build();
        Expense mockRepository = Expense.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .station(station)
                .fuelImport(fuelImport)
                .build();
        ExpenseDTO mockResult = ExpenseDTO.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .station(StationDTO.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImportDTO.builder()
                        .id(1)
                        .name("fuelImport")
                        .createdDate(0L)
                        .build())
                .build();
        Mockito.when(optionalValidate.getExpenseById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, expenseService.delete(1));
    }
}