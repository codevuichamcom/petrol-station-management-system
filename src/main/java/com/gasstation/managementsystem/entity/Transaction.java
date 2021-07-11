package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

    @Column(nullable = false, unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;//Thanh toán bằng thẻ nào

    @ManyToOne
    @JoinColumn(name = "pump_id")
    private Pump pump;

    @ManyToOne
    @JoinColumn(name = "hand_over_ship_id")
    private HandOverShift handOverShift;

}
