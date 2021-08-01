package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Debt;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
import com.gasstation.managementsystem.model.mapper.DebtMapper;
import com.gasstation.managementsystem.repository.DebtRepository;
import com.gasstation.managementsystem.service.DebtService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {
    private final DebtRepository debtRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listDebtToMap(List<Debt> debts) {
        List<DebtDTO> tankDTOS = debts.stream().map(DebtMapper::toDebtDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", tankDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listDebtToMap(debtRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
    }

    @Override
    public DebtDTO findById(int id) throws CustomNotFoundException {
        return DebtMapper.toDebtDTO(optionalValidate.getDebtById(id));
    }
}
