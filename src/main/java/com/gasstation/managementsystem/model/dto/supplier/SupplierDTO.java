package com.gasstation.managementsystem.model.dto.supplier;

import lombok.*;

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

}
