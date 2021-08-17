package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOFilter;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOPay;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOSummaryFilter;
import com.gasstation.managementsystem.model.mapper.DebtMapper;
import com.gasstation.managementsystem.repository.DebtRepository;
import com.gasstation.managementsystem.repository.ReceiptRepository;
import com.gasstation.managementsystem.repository.criteria.DebtRepositoryCriteria;
import com.gasstation.managementsystem.service.DebtService;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
    private final DebtRepository debtRepository;
    private final ReceiptRepository receiptRepository;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;

    @Override
    public HashMap<String, Object> summary(DebtDTOSummaryFilter filter) {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        if (userType.getId() == UserType.OWNER) {
            filter.setStationIds(userHelper.getListStationIdOfOwner(userLoggedIn).toArray(Integer[]::new));
        } else if (userType.getId() == UserType.CUSTOMER) {
            filter.setCustomerId(userLoggedIn.getId());
        }
        return debtCriteria.summary(filter);
    }

    @Override
    public HashMap<String, Object> getDetail(DebtDTOFilter filter) {
        HashMap<String, Object> temp = debtCriteria.getDetail(filter);
        List<Debt> debtList = (List<Debt>) temp.get("data");
        List<DebtDTO> debtDTOList = debtList.stream().map(DebtMapper::toDebtDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", debtDTOList);
        return map;
    }

    @Override
    public void payDebts(List<DebtDTOPay> debtDTOPays) throws CustomNotFoundException {
        if (debtDTOPays == null || debtDTOPays.size() == 0) return;
        User creator = userHelper.getUserLogin();
        for (DebtDTOPay debtDTOPay : debtDTOPays) {
            Debt debt = optionalValidate.getDebtById(debtDTOPay.getDebtId());
            Card card = optionalValidate.getCardById(debtDTOPay.getCardId());
            Transaction transaction = optionalValidate.getTransactionById(debtDTOPay.getTransactionId());
            double amount = debt.getAccountsPayable();
            debtRepository.delete(debt);
            Receipt receipt = Receipt.builder()
                    .amount(amount)
                    .createdDate(DateTimeHelper.getCurrentDate())
                    .reason(StringUtils.trim(debtDTOPay.getReason()))
                    .card(card)
                    .transaction(transaction)
                    .creator(creator).build();
            receiptRepository.save(receipt);
        }
    }

}
