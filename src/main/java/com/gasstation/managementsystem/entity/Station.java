package com.gasstation.managementsystem.entity;

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
}
