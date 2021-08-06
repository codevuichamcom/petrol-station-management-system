package com.gasstation.managementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shift_tbl") //ca bơm
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Shift extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long startTime;

    @Column(nullable = false)
    private Long endTime;

    @OneToMany(mappedBy = "shift")
    private List<WorkSchedule> workScheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "shift")
    private List<PumpShift> pumpShiftList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;
}
