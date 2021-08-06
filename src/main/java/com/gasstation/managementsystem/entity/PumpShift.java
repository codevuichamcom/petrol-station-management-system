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
@Table(name = "pump_shift_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PumpShift extends BaseEntity {

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
