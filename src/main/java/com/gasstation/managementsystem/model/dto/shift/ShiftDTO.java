package com.gasstation.managementsystem.model.dto.shift;

import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShiftDTO {
    private int id;
    private String name;
    private String startTime;
    private String endTime;
    private StationDTO station;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShiftDTO shiftDTO = (ShiftDTO) o;
        return id == shiftDTO.id && Objects.equals(name, shiftDTO.name) && Objects.equals(startTime, shiftDTO.startTime) && Objects.equals(endTime, shiftDTO.endTime);
    }

}
