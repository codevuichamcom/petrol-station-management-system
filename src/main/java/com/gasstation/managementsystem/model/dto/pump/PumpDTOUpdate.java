package com.gasstation.managementsystem.model.dto.pump;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PumpDTOUpdate {
    private String name;
    private String note;
}
