package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date closeShiftDate;

    private String note;

    @ManyToOne
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "pump_id",nullable = false)
    private Pump pump;

    @OneToMany(mappedBy = "handOverShift")
    private List<Transaction> transactionList = new ArrayList<>();

}
