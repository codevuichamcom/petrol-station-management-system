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
public class HandOverShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String note;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @OneToMany(mappedBy = "handOverShip")
    private List<Transaction> transactionList = new ArrayList<>();

}
