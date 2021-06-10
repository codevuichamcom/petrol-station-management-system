package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.ReceiptBill;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReceiptBillDTO {
    private int id;

    private Date date;
    private String submitter;
    private String address;
    private String reason;
    private double amount;
    private String amountInWords;

    public ReceiptBillDTO(ReceiptBill receiptBill) {
//        this.id = receiptBill.getId();
//        this.date = receiptBill.getDate();
//        this.submitter = receiptBill.getSubmitter();
//        this.address = receiptBill.getAddress();
//        this.reason = receiptBill.getReason();
//        this.amount = receiptBill.getAmount();
//        this.amountInWords = receiptBill.getAmountInWords();
    }
}
