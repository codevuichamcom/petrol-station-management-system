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

    private String name;
    private double debtBeginPeriod = 0;//công nợ đầu kì
    private String phone;
    private String address;
    private String note;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<FuelImportBill> fuelImportBillList;// Danh sách phiếu nhập nhiên liệu của chuỗi cung ứng này


}
