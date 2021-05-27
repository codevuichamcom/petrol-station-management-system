package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "fuel_tbl") //nhiên liệu
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String unit;
    private double price;

    @OneToOne
    @JoinColumn(name = "fuel_category_id")//join với fuel_category_tbl
    private FuelCategory fuelCategory; //nhiên liệu này thuộc thể loại nào


}
