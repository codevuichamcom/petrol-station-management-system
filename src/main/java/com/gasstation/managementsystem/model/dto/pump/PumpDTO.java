package com.gasstation.managementsystem.model.dto.pump;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PumpDTO {
    private int id;
    private String name;
    private String note;

}
