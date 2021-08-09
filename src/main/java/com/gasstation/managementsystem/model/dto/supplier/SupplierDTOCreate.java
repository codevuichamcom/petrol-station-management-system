package com.gasstation.managementsystem.model.dto.supplier;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SupplierDTOCreate {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Phone is mandatory")
    private String phone;
    @NotBlank(message = "Address is mandatory")
    private String address;
    private String note;

}
