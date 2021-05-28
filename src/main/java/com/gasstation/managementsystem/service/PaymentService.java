package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Payment;
import com.gasstation.managementsystem.model.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    public List<PaymentDTO> findAll();

    public PaymentDTO findById(int id);

    public PaymentDTO save(Payment payment);

    public PaymentDTO delete(int id);
}
