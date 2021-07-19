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

    public HashMap<String, Object> findAll(TransactionDTOFilter transactionDTOFilter) {
        StringBuilder query = new StringBuilder("select t from Transaction t inner join t.handOverShift h ")
                .append("inner join h.pump p inner join p.tank tank where 1=1 ");
        Integer[] pumpIds = transactionDTOFilter.getPumpIds();
        Integer[] shiftIds = transactionDTOFilter.getShiftIds();
        Integer[] stationIds = transactionDTOFilter.getStationIds();
        Long time = transactionDTOFilter.getTime();
        Double unitPrice = transactionDTOFilter.getUnitPrice();
        Double volume = transactionDTOFilter.getVolume();

        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        qHelper.in("p.id", "pumpIds", pumpIds)
                .in("h.shift.id", "shiftIds", shiftIds)
                .in("h.shift.id", "shiftIds", shiftIds)
                .in("tank.station.id", "stationIds", stationIds)
                .between("t.time", 0L, time, "time", time)
                .between("t.unitPrice", 0.0, unitPrice, "total", unitPrice)
                .between("t.volume", 0.0, volume, "total", volume);
        String countQuery = query.toString().replace("select t", "select count(t.id)");
        Query countTotal = em.createQuery(countQuery);
        qHelper.sort("t.time", "DESC");
        TypedQuery<Transaction> tQuery = em.createQuery(query.toString(), Transaction.class);
        qHelper.getParams().forEach((k, v) -> {
            tQuery.setParameter(k, v);
            countTotal.setParameter(k, v);
        });
        Integer pageIndex = transactionDTOFilter.getPageIndex();
        Integer pageSize = transactionDTOFilter.getPageSize();
        long totalElement = (long) countTotal.getSingleResult();
        long totalPage = totalElement / pageSize;
        if (totalElement % pageSize != 0) {
            totalPage++;
        }
        tQuery.setFirstResult((pageIndex - 1) * pageSize);
        tQuery.setMaxResults(pageSize);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", tQuery.getResultList());
        map.put("totalElement", totalElement);
        map.put("totalPage", totalPage);
        return map;
    }
}
