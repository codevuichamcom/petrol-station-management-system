package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import lombok.*;

import java.util.Optional;

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
    private int numberOfEmployee;

    public StationDTO(Station station) {
        this.id = station.getId();
        this.name = station.getName();
        this.address = station.getAddress();
        User owner = station.getOwner();
        UserTypeDTO userTypeDTO = new UserTypeDTO(owner.getUserType());
        this.owner = UserDTO.builder()
                .id(owner.getId())
                .name(owner.getName())
                .userType(userTypeDTO).build();
        if (station.getEmployeeList() != null) {
            this.numberOfEmployee = station.getEmployeeList().size();
        }
    }
}
