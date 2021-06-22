package com.gasstation.managementsystem.model.dto.station;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class StationDTOCreate {

    @Schema(example = "station1")
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotNull(message = "ownerId is mandatory")
    private Integer ownerId;
    private Double longitude;
    private Double latitude;
}
