package com.gasstation.managementsystem.model.dto.handOverShift;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HandOverShiftDTOCreate {
    @NotNull(message = "shift id is mandatory")
    private Integer shiftId;
    @NotNull(message = "pump id is mandatory")
    private Integer pumpId;

}
