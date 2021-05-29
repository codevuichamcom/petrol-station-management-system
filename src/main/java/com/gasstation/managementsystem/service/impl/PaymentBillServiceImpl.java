package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.PaymentBill;
import com.gasstation.managementsystem.model.dto.PaymentBillDTO;
import com.gasstation.managementsystem.repository.PaymentBillRepository;
import com.gasstation.managementsystem.service.PaymentBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PaymentBillServiceImpl implements PaymentBillService {
    @Autowired
    PaymentBillRepository paymentBillRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<PaymentBill> paymentBills = paymentBillRepository.findAll(pageable);
        List<PaymentBillDTO> paymentBillDTOS = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", paymentBillDTOS);
        map.put("totalElement", paymentBills.getTotalElements());
        map.put("totalPage", paymentBills.getTotalPages());
        return map;
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
