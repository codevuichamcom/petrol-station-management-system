package com.gasstation.managementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "debt_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Debt extends BaseEntity {

    @Column(nullable = false)
    private Double accountsPayable = 0d;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
