package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Supplier;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SupplierDTO {
    private int id;

    private String name;
    private double debtBeginPeriod;//công nợ đầu kì
    private String phone;
    private String address;
    private String note;

    public SupplierDTO(Supplier supplier) {
//        this.id = supplier.getId();
//        this.name = supplier.getName();
//        this.debtBeginPeriod = supplier.getDebtBeginPeriod();
//        this.phone = supplier.getPhone();
//        this.address = supplier.getAddress();
//        this.note = supplier.getNote();
    }
}
