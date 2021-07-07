package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Expense;
import com.gasstation.managementsystem.entity.FuelImport;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTO;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOCreate;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOUpdate;
import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class ExpenseMapper {

    public static ExpenseDTO toExpenseDTO(Expense expense) {
        Station station = expense.getStation();
        FuelImport fuelImport = expense.getFuelImport();
        StationDTO stationDTO = (station != null) ? StationDTO.builder().id(station.getId()).name(station.getName()).build() : null;
        FuelImportDTO fuelImportDTO = (fuelImport != null) ? FuelImportDTO.builder().build() : null;

        return ExpenseDTO.builder()
                .id(expense.getId())
                .reason(expense.getReason())
                .amount(expense.getAmount())
                .date(DateTimeHelper.formatDate(expense.getDate(), "yyyy-MM-dd"))
                .note(expense.getNote())
                .station(stationDTO)
                .fuelImport(fuelImportDTO)
                .build();
    }

    public static Expense toExpense(ExpenseDTOCreate expenseDTOCreate) {
        if (expenseDTOCreate == null) return null;
        return Expense.builder()
                .reason(expenseDTOCreate.getReason())
                .amount(expenseDTOCreate.getAmount())
                .date(expenseDTOCreate.getDate())
                .note(expenseDTOCreate.getNote())
                .build();
    }

    public static void copyNonNullToExpense(Expense api, ExpenseDTOUpdate expenseDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(api, expenseDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
