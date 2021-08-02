package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "receipt_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Long createdDate;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private double discount = 0;

    @Column(nullable = false)
    private double amount = 0;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;// Hóa đơn này do ai tạo

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "debt_id", nullable = false)
    private Debt debt; //Hóa đơn này của ca nào
}
