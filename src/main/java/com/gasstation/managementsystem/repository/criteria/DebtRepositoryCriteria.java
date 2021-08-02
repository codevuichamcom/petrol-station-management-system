package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.model.dto.debt.DebtDTOSummaryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", listResult);
        return map;
    }
}
