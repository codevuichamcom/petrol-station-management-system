package com.gasstation.managementsystem.model.dto.fuelImport;

import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelImportDTO that = (FuelImportDTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(date, that.date) && Objects.equals(volume, that.volume) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(paid, that.paid) && Objects.equals(vatPercent, that.vatPercent) && Objects.equals(note, that.note) && Objects.equals(tank, that.tank) && Objects.equals(supplier, that.supplier);
    }
}