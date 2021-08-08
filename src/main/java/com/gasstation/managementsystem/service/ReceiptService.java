package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOFilter;

import java.util.HashMap;

public interface ReceiptService {
    HashMap<String, Object> findAll(ReceiptDTOFilter filter);

    ReceiptDTO findById(int id) throws CustomNotFoundException;

    ReceiptDTO create(ReceiptDTOCreate receiptDTOCreate) throws CustomNotFoundException;
}
