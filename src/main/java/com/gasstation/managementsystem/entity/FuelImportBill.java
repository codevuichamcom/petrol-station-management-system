package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fuel_import_bill_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FuelImportBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @Column(nullable = false)
    private double numberOfLiters = 0;

    @Column(nullable = false)
    private double pricePerLiter = 0;
    private String note;

    @Column(nullable = false)
    private double vatPercent = 0;

    @ManyToOne
    @JoinColumn(name = "supplier_id")//join với supplier_tbl
    private Supplier supplier;//Nhập hàng từ chuỗi cung ứng nào

    @ManyToOne
    @JoinColumn(name = "tank_id") //join với tank_tbl
    private Tank tank; // Phiếu nhập nhiên liệu này thuộc bể nào


}
