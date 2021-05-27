package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.FuelCategory;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelCategoryDTO {
    private int id;
    private String name;

    public FuelCategoryDTO(FuelCategory fuelCategory) {
        this.id = fuelCategory.getId();
        this.name = fuelCategory.getName();
    }
}
