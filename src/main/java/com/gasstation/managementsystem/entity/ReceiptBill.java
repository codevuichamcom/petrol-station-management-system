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
    private String submitter;

    private String address;
    private String reason;

    @Column(nullable = false)
    private double amount = 0;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User creator;// Hóa đơn này do ai tạo

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift; //Hóa đơn này của ca nào


}
