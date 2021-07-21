package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.entity.Transaction;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOFilter;
import com.gasstation.managementsystem.utils.QueryGenerateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> findAll(TransactionDTOFilter filter) {
        StringBuilder query = new StringBuilder("select t from Transaction t inner join t.handOverShift h ")
                .append("inner join h.pump p inner join p.tank tank where 1=1 ");
        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        qHelper.in("p.id", "pumpIds", filter.getPumpIds())
                .in("h.shift.id", "shiftIds", filter.getShiftIds())
                .in("tank.station.id", "stationIds", filter.getStationIds())
                .between("t.time", 0L, filter.getTime(), "time", filter.getTime())
                .between("t.unitPrice", 0.0, filter.getUnitPrice(), "total", filter.getUnitPrice())
                .between("t.volume", 0.0, filter.getVolume(), "volume", filter.getVolume());
        String countQuery = qHelper.getQuery().toString().replace("select t", "select count(t.id)");
        Query countTotalQuery = em.createQuery(countQuery);
        qHelper.sort("t.time", "DESC");
        TypedQuery<Transaction> tQuery = em.createQuery(query.toString(), Transaction.class);
        return qHelper.paging(tQuery, countTotalQuery, filter.getPageIndex(), filter.getPageSize());
    }
}
