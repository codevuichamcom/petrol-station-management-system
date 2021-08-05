package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pump_shift_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PumpShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private Long createdDate;

    private Long closedTime;

    @ManyToOne
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "pump_id", nullable = false)
    private Pump pump;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private User executor; //người chốt ca

    @OneToMany(mappedBy = "pumpShift")
    private List<Transaction> transactionList = new ArrayList<>();

}
