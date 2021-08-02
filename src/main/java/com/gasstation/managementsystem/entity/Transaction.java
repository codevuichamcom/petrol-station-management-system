package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "transaction_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private long time; //unix time

    @Column(nullable = false)
    private double volume = 0;

    @Column(nullable = false)
    private double unitPrice = 0;

    @Column(nullable = false, unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @ToString.Exclude
    private Card card;//Thanh toán bằng thẻ nào


    @ManyToOne
    @JoinColumn(name = "hand_over_shift_id", nullable = false)
    @ToString.Exclude
    private HandOverShift handOverShift;

    @OneToOne(mappedBy = "transaction")
    private Debt debt;

}
