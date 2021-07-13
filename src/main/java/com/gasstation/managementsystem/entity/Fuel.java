package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fuel_tbl") //nhiên liệu
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private double price = 0;

    @Column(nullable = false)
    private String type = "Xăng";

    @OneToMany(mappedBy = "fuel")
    private List<Tank> tankList;

    @OneToMany(mappedBy = "fuel")
    private List<FuelImport> fuelImportList = new ArrayList<>();

}
