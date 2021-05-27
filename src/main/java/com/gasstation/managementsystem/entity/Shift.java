package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "shift_tbl") //ca bơm
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Time startTime;
    private Time endTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;//Ca bơm đấy của nhân viên nào

    @ManyToOne
    @JoinColumn(name = "owener_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<PumpCode> pumpCodeList;//Danh sách mã bơm của ca này

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<PaymentBill> paymentBillList;//Danh sách hóa đơn thanh toán của ca này

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<Payment> paymentList;//Danh sách thanh toán của ca này

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<ReceiptBill> receiptBillList;//Danh sách hóa đơn nhận của ca này;
}
