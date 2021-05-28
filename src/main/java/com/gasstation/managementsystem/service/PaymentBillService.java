package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.PaymentBill;
import com.gasstation.managementsystem.model.dto.PaymentBillDTO;

import java.util.List;

public interface PaymentBillService {
    public List<PaymentBillDTO> findAll();

    public PaymentBillDTO findById(int id);

    public PaymentBillDTO save(PaymentBill paymentBill);

    public PaymentBillDTO delete(int id);
}
