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
        qHelper
                .between("e.amount", filter.getAmountFrom(), filter.getAmountTo())
                .between("e.createdDate", filter.getCreatedDateFrom(), filter.getCreatedDateTo())
                .like("c.name", "creatorName", filter.getCreatorName())
                .like("s.name", "stationName", filter.getStationName())
                .like("e.reason", "reason", filter.getReason())
                .equal("s.owner.id", "ownerId", filter.getOwnerId());
        String countQuery = qHelper.getQuery().toString().replace("select e", "select count(e.id)");
        Query countTotalQuery = em.createQuery(countQuery);
        qHelper.sort("e.id", "DESC");
        TypedQuery<Expense> tQuery = em.createQuery(query.toString(), Expense.class);
        return qHelper.paging(tQuery, countTotalQuery, filter.getPageIndex(), filter.getPageSize());
    }
}
