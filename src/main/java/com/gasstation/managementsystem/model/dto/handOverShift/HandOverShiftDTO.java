package com.gasstation.managementsystem.model.dto.handOverShift;

import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class HandOverShiftDTO {
    private int id;
    private Long createdDate;
    private Long closedTime;
    private ShiftDTO shift;
    private PumpDTO pump;
    private UserDTO executor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandOverShiftDTO that = (HandOverShiftDTO) o;
        return id == that.id && Objects.equals(createdDate, that.createdDate) && Objects.equals(closedTime, that.closedTime) && Objects.equals(shift, that.shift) && Objects.equals(pump, that.pump) && Objects.equals(executor, that.executor);
    }
}
