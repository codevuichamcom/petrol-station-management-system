package com.gasstation.managementsystem.model.dto.expense;

import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

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
}
