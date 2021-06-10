package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.PaymentBill;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentBillDTO {
    private int id;

    private Date date;
    private String receiver;
    private String address;
    private String reason;
    private double amount;
    private String amountInWords;

    public PaymentBillDTO(PaymentBill paymentBill) {
//        this.id = paymentBill.getId();
//        this.date = paymentBill.getDate();
//        this.receiver = paymentBill.getReceiver();
//        this.address = paymentBill.getAddress();
//        this.reason = paymentBill.getReason();
//        this.amount = paymentBill.getAmount();
//        this.amountInWords = paymentBill.getAmountInWords();
    }
}