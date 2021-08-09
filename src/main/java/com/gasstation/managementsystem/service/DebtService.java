package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOFilter;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOPay;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOSummaryFilter;

import java.util.HashMap;
import java.util.List;

public interface DebtService {

    HashMap<String, Object> summary(DebtDTOSummaryFilter filter);

    HashMap<String, Object> getDetail(DebtDTOFilter filter);

    void payDebts(List<DebtDTOPay> debtDTOPays) throws CustomNotFoundException;
}
