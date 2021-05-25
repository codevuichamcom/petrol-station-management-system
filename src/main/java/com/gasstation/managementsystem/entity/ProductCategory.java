package com.gasstation.managementsystem.entity;

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

}
