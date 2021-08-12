package com.gasstation.managementsystem.model.dto.fuel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelDTOUpdate {
    private String name;
    @Schema(example = "Lít")
    private String unit;
    private Double price;
}
