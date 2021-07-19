package com.gasstation.managementsystem.model.dto.handOverShift;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HandOverShiftDTOCreate {
    private String note;
    @NotNull(message = "createdDate is mandatory")
    private Integer shiftId;
    @NotNull(message = "createdDate is mandatory")
    private Integer pumpId;

}
