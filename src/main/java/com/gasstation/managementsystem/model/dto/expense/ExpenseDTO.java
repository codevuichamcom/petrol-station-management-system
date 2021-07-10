package com.gasstation.managementsystem.model.dto.expense;

import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExpenseDTO {
    private int id;
    private String reason;
    private Double amount;
    private String date;
    private String note;
    private StationDTO station;
    private FuelImportDTO fuelImport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseDTO that = (ExpenseDTO) o;
        return id == that.id && Objects.equals(reason, that.reason) && Objects.equals(amount, that.amount) && Objects.equals(date, that.date) && Objects.equals(note, that.note) && Objects.equals(station, that.station) && Objects.equals(fuelImport, that.fuelImport);
    }

}
