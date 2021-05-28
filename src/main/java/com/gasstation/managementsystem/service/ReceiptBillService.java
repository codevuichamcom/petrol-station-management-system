package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.ReceiptBill;
import com.gasstation.managementsystem.model.dto.ReceiptBillDTO;

import java.util.List;

public interface ReceiptBillService {
    public List<ReceiptBillDTO> findAll();

    public ReceiptBillDTO findById(int id);

    public ReceiptBillDTO save(ReceiptBill receiptBill);

    public ReceiptBillDTO delete(int id);
}
