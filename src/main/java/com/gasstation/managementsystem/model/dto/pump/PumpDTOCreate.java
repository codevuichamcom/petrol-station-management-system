package com.gasstation.managementsystem.model.dto.pump;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PumpDTOCreate {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String note;
    @NotNull(message = "Tank id is mandatory")
    private Integer tankId;

}
