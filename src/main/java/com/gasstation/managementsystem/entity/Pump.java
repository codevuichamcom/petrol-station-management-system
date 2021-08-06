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
@Table(name = "pump_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Pump extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String note;

    @ManyToOne
    @JoinColumn(name = "tank_id", nullable = false) //join with tank_tbl
    private Tank tank;

    @OneToMany(mappedBy = "pump")
    private List<PumpShift> pumpShiftList = new ArrayList<>();

}
