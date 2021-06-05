package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

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

    private Date date;
    private String receiver;
    private String address;
    private String reason;
    private double amount = 0;
    private String amountInWords;

    @ManyToOne
    @JoinColumn(name = "creator") //join với user_tbl
    private User creator;//Hóa đơn thanh toán này được tạo bởi ai

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;//Hóa đơn thanh toán thuộc ca bơm nào

}
