package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.Tank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TankDTO {
    private int id;
    private double volume;
    private double remain;
//    private ProductCategory productCategory;
    private Station station;

    public TankDTO(Tank tank) {
        this.id = tank.getId();
        this.volume = tank.getVolume();
        this.remain = tank.getRemain();
//        this.productCategory = tank.getProductCategory();
        this.station = tank.getStation();
    }
}
