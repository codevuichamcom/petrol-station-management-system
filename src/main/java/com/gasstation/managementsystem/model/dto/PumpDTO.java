package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Pump;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PumpDTO {
    private int id;

    private String name;
    private String note;

    public PumpDTO(Pump pump) {
        this.id = pump.getId();
        this.name = pump.getName();
    }
}
