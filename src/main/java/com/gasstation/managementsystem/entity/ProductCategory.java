package com.gasstation.managementsystem.entity;

import com.gasstation.managementsystem.model.dto.ProductCategoryDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_category_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private List<Tank> tanks;

    public ProductCategory(ProductCategoryDTO productCategoryDTO) {
        this.id = productCategoryDTO.getId();
        this.name = productCategoryDTO.getName();
        this.tanks = productCategoryDTO.getTanks();
    }
}
