package com.gasstation.managementsystem.model.dto.pumpShift;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PumpShiftDTOCreate {
    @NotNull(message = "shift id is mandatory")
    private Integer shiftId;
    @NotNull(message = "pump id is mandatory")
    private Integer pumpId;

}
