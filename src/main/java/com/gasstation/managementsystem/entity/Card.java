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

    private String driverPhone;

    private String driverName;

    @Column(unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private Double initialDebt;

    @Column(nullable = false)
    private Double availableBalance;

    @Column(nullable = false)
    private Double accountsPayable;

    @Column(nullable = false)
    private Double debtLimit;

    private Long limitSetDate;

    private Long createdDate;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "card")
    private List<Transaction> transactionList;//Danh sách mã bơm trả bằng thẻ này

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;//Người nào sử dụng thẻ này

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "card")
    private List<Receipt> receiptList = new ArrayList<>();
}
