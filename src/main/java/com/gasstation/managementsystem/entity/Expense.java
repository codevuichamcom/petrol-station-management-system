package com.gasstation.managementsystem.entity;

import com.gasstation.managementsystem.utils.DateTimeHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "expense_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Expense extends BaseEntity {

    @Column(nullable = false)
    private Long createdDate = DateTimeHelper.getCurrentDate();

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station; //Chi phí này của trạm nào

    @ManyToOne
    @JoinColumn(name = "fuel_import_id")
    private FuelImport fuelImport; //Chi phí này của hóa đơn nhập nhiên liệu nào
}
