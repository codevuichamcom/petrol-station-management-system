package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.Debt;
import com.gasstation.managementsystem.entity.Receipt;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOFilter;
import com.gasstation.managementsystem.model.mapper.ReceiptMapper;
import com.gasstation.managementsystem.repository.DebtRepository;
import com.gasstation.managementsystem.repository.ReceiptRepository;
import com.gasstation.managementsystem.repository.criteria.ReceiptRepositoryCriteria;
import com.gasstation.managementsystem.service.ReceiptService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ReceiptRepositoryCriteria receiptCriteria;
    private final OptionalValidate optionalValidate;
    private final DebtRepository debtRepository;
    private final UserHelper userHelper;


    @Override
    public HashMap<String, Object> findAll(ReceiptDTOFilter filter) {
        HashMap<String, Object> temp = receiptCriteria.findAll(filter);
        List<Receipt> receiptList = (List<Receipt>) temp.get("data");
        List<ReceiptDTO> receiptDTOList = receiptList.stream().map(ReceiptMapper::toReceiptDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", receiptDTOList);
        map.put("totalElement", temp.get("totalElement"));
        map.put("totalPage", temp.get("totalPage"));
        return map;
    }

    @Override
    public ReceiptDTO findById(int id) throws CustomNotFoundException {
        return ReceiptMapper.toReceiptDTO(optionalValidate.getReceiptById(id));
    }
}
