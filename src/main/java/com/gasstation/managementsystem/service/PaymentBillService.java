package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.PaymentBill;
import com.gasstation.managementsystem.model.dto.PaymentBillDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface PaymentBillService {
    public HashMap<String,Object> findAll(Pageable pageable);

    public PaymentBillDTO findById(int id);

    public PaymentBillDTO save(PaymentBill paymentBill);

    public PaymentBillDTO delete(int id);
}
