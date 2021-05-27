package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.PaymentBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentBillRepository extends JpaRepository<PaymentBill, Integer> {
}
