package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private long startTime;

    @Column(nullable = false)
    private long endTime;

    @OneToMany(mappedBy = "shift")
    private List<WorkSchedule> workScheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "shift")
    private List<PumpShift> pumpShiftList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;
}
