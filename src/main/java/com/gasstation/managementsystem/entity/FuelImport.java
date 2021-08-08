package com.gasstation.managementsystem.entity;

import com.gasstation.managementsystem.utils.DateTimeHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fuel_import_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class FuelImport extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long createdDate = DateTimeHelper.getCurrentDate();

    @Column(nullable = false)
    private Long importDate;

    @Column(nullable = false)
    private Double volume;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double amountPaid;

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
