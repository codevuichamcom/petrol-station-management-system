package com.gasstation.managementsystem.model.dto.fuel;

import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelDTO fuelDTO = (FuelDTO) o;
        return id == fuelDTO.id && Objects.equals(name, fuelDTO.name) && Objects.equals(unit, fuelDTO.unit) && Objects.equals(price, fuelDTO.price) && Objects.equals(type, fuelDTO.type);
    }

}
