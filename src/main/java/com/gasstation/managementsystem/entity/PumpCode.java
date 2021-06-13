package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pump_code_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PumpCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time = new Date();

    @Column(nullable = false)
    private double numberOfLiters = 0;

    @Column(nullable = false)
    private double pricePerLiter = 0;

    @ManyToOne
    @JoinColumn(name = "pump_id")
    private Pump pump;//Mã bơm của vòi nào

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;// Mã bơm này của ca nào

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;//Thanh toán bằng thẻ nào
}
