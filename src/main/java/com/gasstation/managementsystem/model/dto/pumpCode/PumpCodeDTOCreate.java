package com.gasstation.managementsystem.model.dto.pumpCode;

import com.gasstation.managementsystem.utils.DateTimeHelper;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PumpCodeDTOCreate {
    @NotNull
    private long time = DateTimeHelper.getCurrentUnixTime();
    @NotNull
    private double numberOfLiters;
    @NotNull
    private double pricePerLiter;
    @NotNull
    private int pumpId;
    @NotNull
    private int shiftId;
    @NotNull
    private int cardId;

}
