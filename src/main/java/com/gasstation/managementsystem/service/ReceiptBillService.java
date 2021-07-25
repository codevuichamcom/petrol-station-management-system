package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;

import java.util.HashMap;

public interface ReceiptBillService {
    HashMap<String, Object> findAll();

    ReceiptDTO findById(int id) throws CustomNotFoundException;

    ReceiptDTO create(ReceiptDTOCreate receiptDTOCreate) throws CustomNotFoundException;
}
