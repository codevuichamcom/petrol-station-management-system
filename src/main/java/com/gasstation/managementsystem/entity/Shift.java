package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "shift_tbl") //ca bơm
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date startTime = new Date();

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date endTime = new Date();

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private Set<WorkSchedule> workScheduleSet = new HashSet<>();

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<HandOverShip> handOverShipList = new ArrayList<>();
}
