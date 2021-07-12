package com.gasstation.managementsystem.model.dto.shift;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShiftDTOCreate {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Start time is mandatory")
    private String startTime;
    @NotNull(message = "End time is mandatory")
    private String endTime;
    @NotNull(message = "End time is mandatory")
    private Integer stationId;
}

