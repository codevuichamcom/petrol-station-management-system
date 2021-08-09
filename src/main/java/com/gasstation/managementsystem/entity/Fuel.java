package com.gasstation.managementsystem.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fuel_tbl") //nhiên liệu
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Fuel extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "fuel")
    private List<Tank> tankList;

    @OneToMany(mappedBy = "fuel")
    private List<FuelImport> fuelImportList = new ArrayList<>();

}
