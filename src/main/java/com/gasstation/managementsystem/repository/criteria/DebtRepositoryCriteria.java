package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.model.dto.debt.DebtDTOSummary;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOSummaryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DebtRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> summary(DebtDTOSummaryFilter filter) {
        String query = "select * from debt_summary";
        Query queryExecutor = em.createNativeQuery(query);

        List<Object[]> listResult = queryExecutor.getResultList();
        List<DebtDTOSummary> debtDTOSummaryList = new ArrayList<>();
        listResult.forEach(objects -> {
            DebtDTOSummary debtDTOSummary = DebtDTOSummary.builder()
                    .cardId((String) objects[0])
                    .stationId((Integer) objects[1])
                    .stationName((String) objects[2])
                    .stationAddress((String) objects[3])
                    .customerId((Integer) objects[4])
                    .customerName((String) objects[5])
                    .customerPhone((String) objects[6])
                    .totalMoney((Double) objects[7])
                    .build();
            debtDTOSummaryList.add(debtDTOSummary);
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", debtDTOSummaryList);
        return map;
    }
}
