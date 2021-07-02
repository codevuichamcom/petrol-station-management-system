package com.gasstation.managementsystem.model.dto.supplier;

import lombok.*;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SupplierDTOUpdate {
    private String name;
    private String phone;
    private String address;
    private String note;

}
