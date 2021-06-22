package com.gasstation.managementsystem.model.dto.station;

import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class StationDTO {
    private int id;
    private String name;
    private String address;
    private UserDTO owner;
    private Integer numberOfEmployee;
    private Double longitude;
    private Double latitude;

}
