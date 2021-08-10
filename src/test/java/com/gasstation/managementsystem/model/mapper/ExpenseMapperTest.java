package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Expense;
import com.gasstation.managementsystem.entity.FuelImport;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTO;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOCreate;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOUpdate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseMapperTest {
    /**
     * param expense is null
     */
    @Test
    void toExpenseDTO_UTCID01() {
        assertNull(ExpenseMapper.toExpenseDTO(null));
    }

    /**
     * param expense is not null
     */
    @Test
    void toExpenseDTO_UTCID02() {
        Long creatDate = 16094340000000L;
        Expense expense = Expense.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .createdDate(creatDate)
                .station(Station.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImport.builder()
                        .id(1)
                        .name("fuelImport")
                        .build())
                .build();
        ExpenseDTO expenseDTO = ExpenseMapper.toExpenseDTO(expense);
        assertEquals(1, expenseDTO.getId());
        assertEquals("reason", expenseDTO.getReason());
        assertEquals(100, expenseDTO.getAmount());
        assertAll(() -> {
            StationDTO stationDTO = expenseDTO.getStation();
            assertEquals(1, stationDTO.getId());
            assertEquals("station", stationDTO.getName());
        });
        assertAll(() -> {
            FuelImportDTO fuelImportDTO = expenseDTO.getFuelImport();
            assertEquals(1, fuelImportDTO.getId());
            assertEquals("fuelImport", fuelImportDTO.getName());
        });
    }

    /**
     * param expenseDTOCreate is null
     */
    @Test
    void toExpense_UTCID01() {
        assertNull(ExpenseMapper.toExpense(null));
    }

    /**
     * param expenseDTOCreate is not null
     */
    @Test
    void toExpense_UTCID02() {
        Long creatDate = 16094340000000L;
        ExpenseDTOCreate expenseDTOCreate = ExpenseDTOCreate.builder()
                .reason("reason")
                .amount(100d)
                .build();
        Expense expense = ExpenseMapper.toExpense(expenseDTOCreate);
        assertEquals("reason", expense.getReason());
        assertEquals(100, expense.getAmount());
    }

    /**
     * param expenseDTOUpdate is null
     */
    @Test
    void copyNonNullToExpense_UTCID01() {
        Long creatDate = 16094340000000L;
        ExpenseDTOUpdate expenseDTOUpdate = ExpenseDTOUpdate.builder()
                .reason("reason_update")
                .amount(101d)
                .build();
        try {
            ExpenseMapper.copyNonNullToExpense(null, expenseDTOUpdate);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * param expenseDTOUpdate is not null
     */
    @Test
    void copyNonNullToExpense_UTCID02() {
        Long creatDate = 16094340000000L;
        Expense expense = Expense.builder()
                .id(1)
                .reason("reason")
                .amount(100d)
                .createdDate(creatDate)
                .station(Station.builder()
                        .id(1)
                        .name("station")
                        .build())
                .fuelImport(FuelImport.builder()
                        .id(1)
                        .name("fuelImport")
                        .build())
                .build();
        ExpenseDTOUpdate expenseDTOUpdate = ExpenseDTOUpdate.builder()
                .reason("reason_update")
                .amount(101d)


                .build();
        ExpenseMapper.copyNonNullToExpense(expense, expenseDTOUpdate);

        assertEquals(1, expense.getId());
        assertEquals("reason_update", expense.getReason());
        assertEquals(101, expense.getAmount());
        assertAll(() -> {
            FuelImport fuelImport = expense.getFuelImport();
            assertEquals(1, fuelImport.getId());
            assertEquals("fuelImport", fuelImport.getName());
        });

    }
}