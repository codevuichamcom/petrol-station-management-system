package com.gasstation.managementsystem.model.dto.station;

import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationDTO that = (StationDTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(owner, that.owner) && Objects.equals(numberOfEmployee, that.numberOfEmployee) && Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude);
    }

}
