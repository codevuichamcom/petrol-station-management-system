package com.gasstation.managementsystem.model.dto.fuelImport;

import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelImportDTO {
    private int id;
    private String name;
    private String date;
    private Double volume;
    private Double unitPrice;
    private Double paid;
    private Double vatPercent;
    private String note;
    private TankDTO tank;
    private SupplierDTO supplier;
}