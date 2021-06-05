package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

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

    private Time time;
    private Time duration;
    private double numberOfLiters = 0;
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
