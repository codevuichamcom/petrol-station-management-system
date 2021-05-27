package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Fuel;
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
    private double price;

    public FuelDTO(Fuel fuel) {
        this.id = fuel.getId();
        this.name = fuel.getName();
        this.unit = fuel.getUnit();
        this.price = fuel.getPrice();
    }
}
