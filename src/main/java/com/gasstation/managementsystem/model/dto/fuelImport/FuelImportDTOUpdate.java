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
    private Double volume;
    private Double unitPrice;
    private Double amountPaid;
    private Double vatPercent;
    private String note;
    private Integer tankId;
    private Integer supplierId;
    private Integer fuelId;
}