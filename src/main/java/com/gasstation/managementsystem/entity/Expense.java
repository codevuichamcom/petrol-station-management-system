package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expense_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private double amount = 0;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station; //CHi phí này của trạm nào

    @ManyToOne
    @JoinColumn(name = "fuel_import_bill_id", nullable = false)
    private FuelImportBill fuelImportBill; //Chi phí này của hóa đơn nhập nhiên liệu nào
}
