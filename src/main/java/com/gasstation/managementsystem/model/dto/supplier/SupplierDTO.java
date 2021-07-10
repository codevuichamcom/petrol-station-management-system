package com.gasstation.managementsystem.model.dto.supplier;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SupplierDTO {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierDTO that = (SupplierDTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(address, that.address) && Objects.equals(note, that.note);
    }

}
