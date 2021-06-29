package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "receipt_bill_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReceiptBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private double discount = 0;

    private String note;

    @Column(nullable = false)
    private double amount = 0;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;// Hóa đơn này do ai tạo

    @ManyToOne
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift; //Hóa đơn này của ca nào

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
}
