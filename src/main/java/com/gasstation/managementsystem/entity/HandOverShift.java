package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hand_over_shift_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HandOverShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private long createdDate;

    private Long closedTime;

    private String note;

    @ManyToOne
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "pump_id", nullable = false)
    private Pump pump;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private User actor; //người chốt ca

    @OneToMany(mappedBy = "handOverShift")
    private List<Transaction> transactionList = new ArrayList<>();

}
