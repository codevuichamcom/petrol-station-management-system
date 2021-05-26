package com.gasstation.managementsystem.entity;

import com.gasstation.managementsystem.model.dto.TankDTO;
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

    public Tank(TankDTO tankDTO) {
        this.id = tankDTO.getId();
        this.volume = tankDTO.getVolume();
        this.remain = tankDTO.getRemain();
        this.productCategory = tankDTO.getProductCategory();
        this.station = tankDTO.getStation();
    }
}
