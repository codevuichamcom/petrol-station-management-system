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
    @NotNull(message = "time is mandatory")
    private Long time = DateTimeHelper.getCurrentUnixTime();
    @NotNull(message = "number of litter is mandatory")
    private Double numberOfLiters;
    @NotNull(message = "pricePerLitter is mandatory")
    private Double pricePerLiter;
    @NotNull(message = "Pump id is mandatory")
    private Integer pumpId;
    @NotNull(message = "Shift Id is mandatory")
    private Integer shiftId;
    @NotNull(message = "Card ìd is mandatory")
    private Integer cardId;

}
