package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.entity.Expense;
import com.gasstation.managementsystem.model.dto.expense.ExpenseDTOFilter;
import com.gasstation.managementsystem.utils.QueryGenerateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;

@Repository
@RequiredArgsConstructor
public class ExpenseRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> findAll(ExpenseDTOFilter filter) {
        StringBuilder query = new StringBuilder("select e from Expense e inner join e.station s inner join e.creator c where 1=1");
        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        qHelper.like("e.reason", "reason", filter.getReason())
                .between("e.amount", 0d, filter.getAmount())
                .between("e.createdDate", 0L, filter.getCreatedDate())
                .like("c.name", "creatorName", filter.getCreatorName())
                .in("s.id", "stationIds", filter.getStationIds());
        String countQuery = qHelper.getQuery().toString().replace("select e", "select count(e.id)");
        Query countTotalQuery = em.createQuery(countQuery);
        qHelper.sort("e.createdDate", "DESC");
        TypedQuery<Expense> tQuery = em.createQuery(query.toString(), Expense.class);
        return qHelper.paging(tQuery, countTotalQuery, filter.getPageIndex(), filter.getPageSize());
    }
}
