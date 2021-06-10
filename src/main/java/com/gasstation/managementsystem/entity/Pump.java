package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pump_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pump {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String note;

    @ManyToOne
    @JoinColumn(name = "tank_id") //join with tank_tbl
    private Tank tank;

    @OneToMany(mappedBy = "pump",cascade = CascadeType.ALL)
    private List<PumpCode> pumpCodeList;//Danh sách mã bơm của vòi này



}
