package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.ReceiptBill;
import com.gasstation.managementsystem.model.dto.ReceiptBillDTO;
import com.gasstation.managementsystem.repository.ReceiptBillRepository;
import com.gasstation.managementsystem.service.ReceiptBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReceiptBillServiceImpl implements ReceiptBillService {
    @Autowired
    ReceiptBillRepository receiptBillRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<ReceiptBill> receiptBills = receiptBillRepository.findAll(pageable);
        List<ReceiptBillDTO> receiptBillDTOS = new ArrayList<>();
        for (ReceiptBill receiptBill : receiptBills) {
            receiptBillDTOS.add(new ReceiptBillDTO(receiptBill));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", receiptBillDTOS);
        map.put("totalElement", receiptBills.getTotalElements());
        map.put("totalPage", receiptBills.getTotalPages());
        return map;
    }

    @Override
    public ReceiptBillDTO findById(int id) {
        return new ReceiptBillDTO(receiptBillRepository.findById(id).get());
    }

    @Override
    public ReceiptBillDTO save(ReceiptBill receiptBill) {
        receiptBillRepository.save(receiptBill);
        return new ReceiptBillDTO(receiptBill);
    }

    @Override
    public ReceiptBillDTO delete(int id) {
        ReceiptBill receiptBill = receiptBillRepository.findById(id).get();
        if (receiptBill != null) {
            receiptBillRepository.delete(receiptBill);
            return new ReceiptBillDTO(receiptBill);
        }
        return null;
    }
}
