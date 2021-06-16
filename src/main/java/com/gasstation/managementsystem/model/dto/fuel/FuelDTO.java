package com.gasstation.managementsystem.model.dto.fuel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelDTO {
    private int id;
    private String name;
    private String unit;
    private Double price;
    private String type;
}
