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

    @OneToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction; //Hóa đơn này của ca nào

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(createdDate, receipt.createdDate) && Objects.equals(reason, receipt.reason) && Objects.equals(amount, receipt.amount) && Objects.equals(creator, receipt.creator) && Objects.equals(card, receipt.card) && Objects.equals(transaction, receipt.transaction);
    }

}
