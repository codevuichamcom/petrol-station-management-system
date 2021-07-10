package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time = new Date();

    @Column(nullable = false)
    private double volume = 0;

    @Column(nullable = false)
    private double unitPrice = 0;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;//Thanh toán bằng thẻ nào

    @ManyToOne
    @JoinColumn(name = "pump_id")
    private Pump pump;

    @ManyToOne
    @JoinColumn(name = "hand_over_ship_id")
    private HandOverShip handOverShip;

}
