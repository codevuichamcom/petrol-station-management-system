package com.gasstation.managementsystem.model.dto.station;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class StationDTOUpdate {

    @Schema(example = "station1")
    private String name;
    private String address;
    private Integer ownerId;
    private Double longitude;
    private Double latitude;
}
