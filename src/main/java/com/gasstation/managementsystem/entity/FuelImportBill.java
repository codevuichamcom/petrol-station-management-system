package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

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

    private String name;
    private Date date;
    private double numberOfLiters;
    private double pricePerLiter;
    private String note;
    private double vatPercent;

    @ManyToOne
    @JoinColumn(name = "supplier_id")//join với supplier_tbl
    private Supplier supplier;//Nhập hàng từ chuỗi cung ứng nào

    @ManyToOne
    @JoinColumn(name = "tank_id") //join với tank_tbl
    private Tank tank; // Phiếu nhập nhiên liệu này thuộc bể nào


}
