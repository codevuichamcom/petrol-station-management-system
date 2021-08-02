package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private Double initialDebt;
    @Column(nullable = false)
    private Double availableBalance;
    @Column(nullable = false)
    private Double accountsPayable;

    @Column(nullable = false)
    private Double debtLimit;

    private Long limitSetDate;

    private Long issuedDate;

    private Long activeDate;

    @OneToMany(mappedBy = "card")
    private List<Transaction> transactionList;//Danh sách mã bơm trả bằng thẻ này

    @ManyToOne
    @JoinColumn(name = "activate_user_id")
    private User activateUser; //Ai active thẻ này

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;//Người nào sử dụng thẻ này

    @OneToMany(mappedBy = "card")
    private List<Receipt> receiptList = new ArrayList<>();
}
