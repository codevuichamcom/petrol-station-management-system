package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.entity.Transaction;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> findAll(TransactionDTOFilter transactionDTOFilter) {
        String query = "select t from Transaction t inner join t.handOverShift h " +
                "inner join h.pump p inner join p.tank tank where 1=1 ";
        Integer[] pumpIds = transactionDTOFilter.getPumpIds();
        Integer[] shiftIds = transactionDTOFilter.getShiftIds();
        Integer[] stationIds = transactionDTOFilter.getStationIds();
        Long time = transactionDTOFilter.getTime();
        Double total = transactionDTOFilter.getTotal();
        Double unitPrice = transactionDTOFilter.getUnitPrice();
        Double volume = transactionDTOFilter.getVolume();
        if (pumpIds != null) {
            query += "and p.id in (:pumpIds) ";
        }
        if (shiftIds != null) {
            query += "and h.shift.id in (:shiftIds) ";
        }
        if (stationIds != null) {
            query += "and tank.station.id in (:stationIds) ";
        }
        if (time != null) {
            query += "and t.time = :time ";
        }
        if (total != null) {
            query += "and t.total = :total ";
        }
        if (unitPrice != null) {
            query += "and t.unitPrice = :unitPrice ";
        }
        if (volume != null) {
            query += "and t.volume = :volume ";
        }
        String countQuery = query.replace("select t", "select count(t.id)");
        Query countTotal = em.createQuery(countQuery);
        TypedQuery<Transaction> tQuery = em.createQuery(query, Transaction.class);
        if (pumpIds != null) {
            tQuery.setParameter("pumpIds", Arrays.asList(pumpIds));
            countTotal.setParameter("pumpIds", Arrays.asList(pumpIds));

        }
        if (shiftIds != null) {
            tQuery.setParameter("shiftIds", Arrays.asList(shiftIds));
            countTotal.setParameter("shiftIds", Arrays.asList(shiftIds));
        }
        if (stationIds != null) {
            tQuery.setParameter("stationIds", Arrays.asList(stationIds));
            countTotal.setParameter("stationIds", Arrays.asList(stationIds));
        }
        if (time != null) {
            tQuery.setParameter("time", time);
            countTotal.setParameter("time", time);
        }
        if (total != null) {
            tQuery.setParameter("total", total);
            countTotal.setParameter("total", total);
        }
        if (unitPrice != null) {
            tQuery.setParameter("unitPrice", unitPrice);
            countTotal.setParameter("unitPrice", unitPrice);
        }
        if (volume != null) {
            tQuery.setParameter("volume", volume);
            countTotal.setParameter("volume", volume);
        }
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
