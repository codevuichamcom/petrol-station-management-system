package com.gasstation.managementsystem.model.dto.fuel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelDTOUpdate {
    @Length(message = "Length of name must be greater than 3 characters", min = 4)
    private String name;
    @Schema(example = "Lít")
    private String unit;
    private Double price;
    private String type;
}
