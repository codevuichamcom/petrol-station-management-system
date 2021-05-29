package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.ReceiptBill;
import com.gasstation.managementsystem.model.dto.ReceiptBillDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface ReceiptBillService {
    public HashMap<String,Object> findAll(Pageable pageable);

    public ReceiptBillDTO findById(int id);

    public ReceiptBillDTO save(ReceiptBill receiptBill);

    public ReceiptBillDTO delete(int id);
}
