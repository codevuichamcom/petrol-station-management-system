package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.PaymentBill;
import com.gasstation.managementsystem.model.dto.PaymentBillDTO;
import com.gasstation.managementsystem.repository.PaymentBillRepository;
import com.gasstation.managementsystem.service.PaymentBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentBillServiceImpl implements PaymentBillService {
    @Autowired
    PaymentBillRepository paymentBillRepository;

    @Override
    public List<PaymentBillDTO> findAll() {
        List<PaymentBill> paymentBills = paymentBillRepository.findAll();
        List<PaymentBillDTO> paymentBillDTOS = new ArrayList<>();
        for (PaymentBill paymentBill : paymentBills) {
            paymentBillDTOS.add(new PaymentBillDTO(paymentBill));
        }
        return paymentBillDTOS;
    }

    @Override
    public PaymentBillDTO findById(int id) {
        return new PaymentBillDTO(paymentBillRepository.findById(id).get());
    }

    @Override
    public PaymentBillDTO save(PaymentBill paymentBill) {
        paymentBillRepository.save(paymentBill);
        return new PaymentBillDTO(paymentBill);
    }

    @Override
    public PaymentBillDTO delete(int id) {
        PaymentBill paymentBill = paymentBillRepository.findById(id).get();
        if (paymentBill != null) {
            paymentBillRepository.delete(paymentBill);
            return new PaymentBillDTO(paymentBill);
        }
        return null;
    }
}
