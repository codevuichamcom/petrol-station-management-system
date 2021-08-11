package com.gasstation.managementsystem.model.dto.fuelImport;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelImportDTOUpdate {
    private String name;
    private Long importDate;
    private Double amountPaid;
    private String note;
}