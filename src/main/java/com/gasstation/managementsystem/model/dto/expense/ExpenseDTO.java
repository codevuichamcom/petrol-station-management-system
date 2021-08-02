package com.gasstation.managementsystem.model.dto.expense;

import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ExpenseDTO {
    private int id;
    private String reason;
    private Double amount;
    private Long createdDate;
    private StationDTO station;
    private FuelImportDTO fuelImport;
    private UserDTO creator;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseDTO that = (ExpenseDTO) o;
        return id == that.id && Objects.equals(reason, that.reason) && Objects.equals(amount, that.amount) && Objects.equals(createdDate, that.createdDate) && Objects.equals(station, that.station) && Objects.equals(fuelImport, that.fuelImport) && Objects.equals(creator, that.creator);
    }
}
