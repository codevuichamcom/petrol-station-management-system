package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Payment;
import com.gasstation.managementsystem.model.dto.PaymentDTO;
import com.gasstation.managementsystem.repository.PaymentRepository;
import com.gasstation.managementsystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Payment> payments = paymentRepository.findAll(pageable);
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDTOS.add(new PaymentDTO(payment));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", paymentDTOS);
        map.put("totalElement", payments.getTotalElements());
        map.put("totalPage", payments.getTotalPages());
        return map;
    }

    @Override
    public PaymentDTO findById(int id) {
        return new PaymentDTO(paymentRepository.findById(id).get());
    }

    @Override
    public PaymentDTO save(Payment payment) {
        paymentRepository.save(payment);
        return new PaymentDTO(payment);
    }

    @Override
    public PaymentDTO delete(int id) {
        Payment payment = paymentRepository.findById(id).get();
        if (payment != null) {
            paymentRepository.delete(payment);
            return new PaymentDTO(payment);
        }
        return null;
    }
}
