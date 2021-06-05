package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

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

    private Date date;
    private String submitter;
    private String address;
    private String reason;
    private double amount = 0;
    private String amountInWords;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User creator;// Hóa đơn này do ai tạo

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift; //Hóa đơn này của ca nào


}
