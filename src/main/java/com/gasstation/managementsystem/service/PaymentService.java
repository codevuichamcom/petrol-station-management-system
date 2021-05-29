package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Payment;
import com.gasstation.managementsystem.model.dto.PaymentDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface PaymentService {
    public HashMap<String,Object> findAll(Pageable pageable);

    public PaymentDTO findById(int id);

    public PaymentDTO save(Payment payment);

    public PaymentDTO delete(int id);
}
