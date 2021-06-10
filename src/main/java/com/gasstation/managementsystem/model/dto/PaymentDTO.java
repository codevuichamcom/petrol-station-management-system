package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Payment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentDTO {
    private int id;

    private double amount;
    private double discounts;//chiết khấu
    private String note;

    public PaymentDTO(Payment payment) {
//        this.id = payment.getId();
//        this.amount = payment.getAmount();
//        this.discounts = payment.getDiscounts();
//        this.note = payment.getNote();
    }
}
