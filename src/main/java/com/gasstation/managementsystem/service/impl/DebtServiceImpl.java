package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Debt;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOFilter;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOSummaryFilter;
import com.gasstation.managementsystem.model.mapper.DebtMapper;
import com.gasstation.managementsystem.repository.criteria.DebtRepositoryCriteria;
import com.gasstation.managementsystem.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {
    private final DebtRepositoryCriteria debtCriteria;

    @Override
    public HashMap<String, Object> summary(DebtDTOSummaryFilter filter) {
        return debtCriteria.summary(filter);
    }

    @Override
    public HashMap<String, Object> getDetail(DebtDTOFilter filter) throws CustomNotFoundException {
        HashMap<String, Object> temp = debtCriteria.getDetail(filter);
        List<Debt> debtList = (List<Debt>) temp.get("data");
        List<DebtDTO> debtDTOList = debtList.stream().map(DebtMapper::toDebtDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", debtDTOList);
        return map;
    }

}
