package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    @JoinColumn(name = "tank_id", nullable = false) //join with tank_tbl
    private Tank tank;

    @OneToMany(mappedBy = "pump")
    private List<Transaction> transactionList = new ArrayList<>();
}
