package com.gasstation.managementsystem.model.dto.fuelImport;

import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
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
    private Long createdDate;
    private Long importDate;
    private Double volume;
    private Double unitPrice;
    private Double amountPaid;
    private Double vatPercent;
    private String note;
    private TankDTO tank;
    private SupplierDTO supplier;
    private UserDTO creator;
    private FuelDTO fuel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelImportDTO that = (FuelImportDTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(createdDate, that.createdDate) && Objects.equals(volume, that.volume) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(amountPaid, that.amountPaid) && Objects.equals(vatPercent, that.vatPercent) && Objects.equals(note, that.note) && Objects.equals(tank, that.tank) && Objects.equals(supplier, that.supplier) && Objects.equals(creator, that.creator) && Objects.equals(fuel, that.fuel);
    }
}