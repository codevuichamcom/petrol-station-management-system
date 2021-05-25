package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tank_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Tank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double volume;
    private double remain;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

}
