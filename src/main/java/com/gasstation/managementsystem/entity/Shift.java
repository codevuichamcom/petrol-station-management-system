package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    @Temporal(TemporalType.TIME)
    private Date startTime = new Date();

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date endTime = new Date();

    @OneToMany(mappedBy = "shift")
    private List<WorkSchedule> workScheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "shift")
    private List<HandOverShift> handOverShiftList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;
}
