package com.gasstation.managementsystem.entity;

import com.gasstation.managementsystem.model.dto.StationDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "station_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<Tank> tanks;

    public Station(StationDTO stationDTO) {
        this.id = stationDTO.getId();
        this.user = stationDTO.getUser();
        this.tanks = stationDTO.getTanks();
        this.address = stationDTO.getAddress();
        this.tanks = stationDTO.getTanks();
    }
}
