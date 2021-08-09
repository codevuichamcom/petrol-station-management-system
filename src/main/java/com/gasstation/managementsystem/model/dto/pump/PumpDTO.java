package com.gasstation.managementsystem.model.dto.pump;

import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PumpDTO {
    private int id;
    private String name;
    private String note;
    private TankDTO tank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PumpDTO pumpDTO = (PumpDTO) o;
        return id == pumpDTO.id && Objects.equals(name, pumpDTO.name) && Objects.equals(note, pumpDTO.note) && Objects.equals(tank, pumpDTO.tank);
    }
}
