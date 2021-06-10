package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "payment_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double amount = 0;

    @Column(nullable = false)
    private double discounts = 0;//chiết khấu

    private String note;

    @ManyToOne
    @JoinColumn(name = "customer_id") //join với user_tbl
    private User customer;//Thanh toán này của khách hàng nào

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;//Thanh toán bằng thẻ nào

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;//Thanh toán cho ca nào


}
