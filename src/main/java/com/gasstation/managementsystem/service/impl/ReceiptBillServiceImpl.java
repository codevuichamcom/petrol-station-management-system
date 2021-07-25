package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Receipt;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;
import com.gasstation.managementsystem.model.mapper.ReceiptMapper;
import com.gasstation.managementsystem.repository.ReceiptRepository;
import com.gasstation.managementsystem.service.ReceiptBillService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiptBillServiceImpl implements ReceiptBillService {
    private final ReceiptRepository receiptRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listReceiptToMap(List<Receipt> receipts) {
        List<ReceiptDTO> receiptDTOS = receipts.stream().map(ReceiptMapper::toReceiptDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", receiptDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listReceiptToMap(receiptRepository.findAll(Sort.by(Sort.Direction.DESC, "date")));
    }

    @Override
    public ReceiptDTO findById(int id) throws CustomNotFoundException {
        return ReceiptMapper.toReceiptDTO(optionalValidate.getReceiptById(id));
    }

    @Override
    public ReceiptDTO create(ReceiptDTOCreate receiptDTOCreate) throws CustomNotFoundException {
        Receipt receipt = ReceiptMapper.toReceipt(receiptDTOCreate);
        receipt.setCreator(optionalValidate.getUserById(receiptDTOCreate.getCreatorId()));
        receipt.setCard(optionalValidate.getCardById(receiptDTOCreate.getCardId()));
        receipt = receiptRepository.save(receipt);
        return ReceiptMapper.toReceiptDTO(receipt);
    }
}
