package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "fuel_import_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FuelImport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @Column(nullable = false)
    private double volume = 0;

    @Column(nullable = false)
    private double unitPrice = 0;

    @Column(nullable = false)
    private double paid = 0;

    private String note;

    @Column(nullable = false)
    private double vatPercent = 0;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)//join với supplier_tbl
    private Supplier supplier;//Nhập hàng từ chuỗi cung ứng nào

    @ManyToOne
    @JoinColumn(name = "tank_id", nullable = false) //join với tank_tbl
    private Tank tank; // Phiếu nhập nhiên liệu này thuộc bể nào

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "fuel_id", nullable = false)
    private Fuel fuel;

    @OneToMany(mappedBy = "fuelImport")
    private List<Expense> expenseList = new ArrayList<>();//Danh sách chi phí của hóa đơn này

}
