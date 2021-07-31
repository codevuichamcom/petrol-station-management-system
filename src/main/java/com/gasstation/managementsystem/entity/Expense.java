package com.gasstation.managementsystem.entity;

import com.gasstation.managementsystem.utils.DateTimeHelper;
import lombok.*;

import javax.persistence.*;

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

    @Column(nullable = false)
    private long createdDate = DateTimeHelper.getCurrentDate();

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
    @JoinColumn(name = "fuel_import_id", nullable = false)
    private FuelImport fuelImport; //Chi phí này của hóa đơn nhập nhiên liệu nào
}
