package com.gasstation.managementsystem.model.dto.shift;

import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShiftDTO {
    private int id;
    private long startTime;
    private long endTime;

    private UserDTO employee;
    private UserDTO owner;
    private StationDTO station;

}
