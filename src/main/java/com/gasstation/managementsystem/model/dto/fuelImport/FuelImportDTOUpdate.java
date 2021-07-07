package com.gasstation.managementsystem.model.dto.fuelImport;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelImportDTOUpdate {
    private String name;
    private Date date;
    private Double volume;
    private Double unitPrice;
    private Double paid;
    private Double liability;
    private Double vatPercent;
    private String note;
    private Integer tankId;
    private Integer supplierId;
}