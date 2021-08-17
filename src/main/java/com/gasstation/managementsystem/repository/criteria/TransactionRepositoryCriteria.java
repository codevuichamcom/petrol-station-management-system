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
        StringBuilder query = new StringBuilder("select t from Transaction t inner join t.pumpShift h ")
                .append("inner join h.pump p inner join p.tank tank where 1=1 ");
        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        qHelper
                .between("t.time", filter.getTimeFrom(), filter.getTimeTo())
                .between("t.unitPrice", filter.getUnitPriceFrom(), filter.getUnitPriceTo())
                .between("t.volume", filter.getVolumeFrom(), filter.getVolumeTo())
                .between("t.totalAmount", filter.getAmountFrom(), filter.getAmountTo())
                .like("p.name", "pumpName", filter.getPumpName())
                .like("h.shift.name", "shiftName", filter.getShiftName())
                .like("tank.station.name", "stationName", filter.getStationName())
                .in("tank.station.id", "stationIds", filter.getStationIds())
                .equal("t.card.customer.id", "customerId", filter.getCustomerId());
        String countQuery = qHelper.getQuery().toString().replace("select t", "select count(t.id)");
        Query countTotalQuery = em.createQuery(countQuery);
        String totalVolumeAndAmountQuery = qHelper.getQuery().toString().replace("select t", "select coalesce(sum(t.volume), 0), coalesce(sum(t.totalAmount), 0)");
        QueryGenerateHelper volumeAmountHelper = new QueryGenerateHelper();
        volumeAmountHelper.setQuery(new StringBuilder(totalVolumeAndAmountQuery));
        volumeAmountHelper.setParams(qHelper.getParams());
        Query volumeAmountQuery = em.createQuery(totalVolumeAndAmountQuery);
        volumeAmountHelper.setValueToParams(volumeAmountQuery);
        Object[] volumeAndAmount = (Object[]) volumeAmountQuery.getSingleResult();
        qHelper.sort("t.id", "DESC");
        TypedQuery<Transaction> tQuery = em.createQuery(query.toString(), Transaction.class);
        HashMap<String, Object> map = qHelper.paging(tQuery, countTotalQuery, filter.getPageIndex(), filter.getPageSize());
        final int TOTAL_VOLUME = 0;
        final int TOTAL_AMOUNT = 1;
        map.put("totalVolume", volumeAndAmount[TOTAL_VOLUME]);
        map.put("totalAmount", volumeAndAmount[TOTAL_AMOUNT]);
        return map;
    }
}
