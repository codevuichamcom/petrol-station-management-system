package com.gasstation.managementsystem.model.dto.shift;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShiftDTOCreate {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Start time is mandatory")
    private Date startTime;
    @NotNull(message = "End time is mandatory")
    private Date endTime;
}

