package com.gasstation.managementsystem.model.dto.handOverShift;

import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HandOverShiftDTO {
    private int id;
    private String status;
    private LocalDateTime localDateTime;
    private String note;
    private ShiftDTO shift;
    private PumpDTO pump;

}
