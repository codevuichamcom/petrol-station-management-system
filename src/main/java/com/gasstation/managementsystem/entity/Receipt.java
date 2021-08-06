package com.gasstation.managementsystem.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "receipt_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class Receipt extends BaseEntity {

    @Column(nullable = false)
    private Long createdDate;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;// Hóa đơn này do ai tạo

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "debt_id", nullable = false)
    private Debt debt; //Hóa đơn này của ca nào

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return id == receipt.id && Double.compare(receipt.amount, amount) == 0 && Objects.equals(createdDate, receipt.createdDate) && Objects.equals(reason, receipt.reason) && Objects.equals(creator, receipt.creator) && Objects.equals(card, receipt.card) && Objects.equals(debt, receipt.debt);
    }
}
