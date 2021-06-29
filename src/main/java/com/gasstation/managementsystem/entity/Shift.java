package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime = new Date();

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime = new Date();

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<Transaction> transactionList;//Danh sách mã bơm của ca này

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL)
    private List<ReceiptBill> receiptBillList;//Danh sách hóa đơn nhận của ca này;

    @ManyToMany(mappedBy = "shiftList")
    private Set<User> employeeList = new HashSet<>();
}
