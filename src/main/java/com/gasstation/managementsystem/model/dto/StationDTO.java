package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.entity.User;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class StationDTO {
    private int id;
    private String name;
    private String address;
    private User user;
    private List<Tank> tanks;

    public StationDTO(Station station) {
        this.id = station.getId();
        this.user = station.getUser();
        this.tanks = station.getTanks();
        this.address = station.getAddress();
        this.tanks = station.getTanks();
    }
}
