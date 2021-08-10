package com.gasstation.managementsystem.model.dto.fuel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelDTOCreate {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Schema(example = "Lít")
    @NotBlank(message = "Unit is mandatory")
    private String unit;
    @NotNull(message = "Price is mandatory")
    private Double price;
    @NotBlank(message = "Type is mandatory")
    private String type = "Xăng";
}
