package com.gasstation.managementsystem.model.dto.handOverShift;

import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HandOverShiftDTO {
    private int id;
    private Long createdDate;
    private Long closedTime;
    private ShiftDTO shift;
    private PumpDTO pump;
    private UserDTO executor;

}
