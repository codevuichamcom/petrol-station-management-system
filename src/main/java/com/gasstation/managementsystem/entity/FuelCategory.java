package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fuel_category_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FuelCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(mappedBy = "fuelCategory", cascade = CascadeType.ALL)
    private Fuel fuel;

    @OneToMany(mappedBy = "fuelCategory", cascade = CascadeType.ALL)
    private List<Tank> tankList;//Danh sách ác bể của thể loại này


}
