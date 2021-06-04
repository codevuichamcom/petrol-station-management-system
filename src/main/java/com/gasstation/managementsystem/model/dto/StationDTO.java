package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Station;
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
    private int ownerId;
    private String ownerName;
    private int numberOfEmployee;

    public StationDTO(Station station) {
        this.id = station.getId();
        this.name = station.getName();
        this.address = station.getAddress();
        this.ownerId = station.getOwner().getId();
        this.ownerName = station.getOwner().getName();
        this.numberOfEmployee = Optional.ofNullable(station.getEmployeeList().size()).orElse(0);
    }
}
