package com.gasstation.managementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "transaction_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Transaction extends BaseEntity {

    @Column(nullable = false)
    private Long time; //unix time

    @Column(nullable = false)
    private Double volume;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;//Thanh toán bằng thẻ nào


    @ManyToOne
    @JoinColumn(name = "pump_shift_id", nullable = false)
    private PumpShift pumpShift;

    @OneToOne(mappedBy = "transaction")
    private Debt debt;

    @OneToOne(mappedBy = "transaction")
    private Receipt receipt;

}
