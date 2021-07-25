package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "card_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Card {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String driverPhone;

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private String licensePalates;

    @Column(nullable = false)
    private double initialDebt = 0;
    @Column(nullable = false)
    private double availableBalance = 0;
    @Column(nullable = false)
    private double outstandingBalance = 0;

    @Column(nullable = false)
    private double debtLimit = 0;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date limitSetDate = new Date();

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date issuedDate;

    @Temporal(TemporalType.DATE)
    private Date activeDate;

    @OneToMany(mappedBy = "card")
    private List<Transaction> transactionList;//Danh sách mã bơm trả bằng thẻ này

    @ManyToOne
    @JoinColumn(name = "activate_user_id", nullable = false)
    private User activateUser; //Ai active thẻ này

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;//Người nào sử dụng thẻ này

    @OneToMany(mappedBy = "card")
    private List<Receipt> receiptList = new ArrayList<>();

    @OneToMany(mappedBy = "card")
    private List<Debt> debtList = new ArrayList<>();
}
