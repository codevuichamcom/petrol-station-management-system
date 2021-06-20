package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.PumpCode;
import lombok.*;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PumpCodeDTO {
    private int id;

    private Time time;
    private Time duration;
    private double numberOfLiters;
    private double pricePerLiter;

    public PumpCodeDTO(PumpCode pumpCode) {
//        this.id = pumpCode.getId();
//        this.time = pumpCode.getTime();
//        this.duration = pumpCode.getDuration();
//        this.numberOfLiters = pumpCode.getNumberOfLiters();
//        this.pricePerLiter = pumpCode.getPricePerLiter();
    }
}
