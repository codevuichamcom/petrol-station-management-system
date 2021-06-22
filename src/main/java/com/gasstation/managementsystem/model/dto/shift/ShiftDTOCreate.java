package com.gasstation.managementsystem.model.dto.shift;

import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShiftDTOCreate {
    @NotNull(message = "Start time is mandatory")
    private long startTime;
    @NotNull(message = "End time is mandatory")
    private long endTime;
    @NotNull(message = "Employee id is mandatory")
    private int employeeId;
    @NotNull(message = "Owner id is mandatory")
    private int ownerId;
    @NotNull(message = "Station id is mandatory")
    private int stationId;

}
