package com.gasstation.managementsystem.model.dto.pumpCode;

import com.gasstation.managementsystem.utils.DateTimeHelper;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PumpCodeDTOUpdate {
    private Long time = DateTimeHelper.getCurrentUnixTime();
    private Double numberOfLiters;
    private Double pricePerLiter;
    private Integer pumpId;
    private Integer shiftId;
    private Integer cardId;

}
