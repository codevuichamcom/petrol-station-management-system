package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "supplier_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double debtBeginPeriod = 0;//công nợ đầu kì

    @Column(nullable = false,unique = true)
    private String phone;

    @Column(nullable = false)
    private String address;

    private String note;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<FuelImportBill> fuelImportBillList;// Danh sách phiếu nhập nhiên liệu của chuỗi cung ứng này


}
