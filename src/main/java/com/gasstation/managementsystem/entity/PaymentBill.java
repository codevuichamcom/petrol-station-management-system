package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_bill_tbl") //hóa đơn thanh toán
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PaymentBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @Column(nullable = false)
    private String receiver;

    private String address;
    private String reason;

    @Column(nullable = false)
    private double amount = 0;

    @ManyToOne
    @JoinColumn(name = "creator") //join với user_tbl
    private User creator;//Hóa đơn thanh toán này được tạo bởi ai

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;//Hóa đơn thanh toán thuộc ca bơm nào

}
