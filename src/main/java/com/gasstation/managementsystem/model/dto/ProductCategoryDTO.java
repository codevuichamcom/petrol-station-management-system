package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.ProductCategory;
import com.gasstation.managementsystem.entity.Tank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ProductCategoryDTO {
    private int id;
    private String name;
    private List<Tank> tanks;

    public ProductCategoryDTO(ProductCategory productCategory) {
        this.id = productCategory.getId();
        this.name = productCategory.getName();
        this.tanks = productCategory.getTanks();
    }
}
