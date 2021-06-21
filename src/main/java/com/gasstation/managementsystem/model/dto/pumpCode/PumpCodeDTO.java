package com.gasstation.managementsystem.model.dto.pumpCode;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PumpCodeDTO {
    private int id;
    private long time;
    private double numberOfLiters;
    private double pricePerLiter;

}
