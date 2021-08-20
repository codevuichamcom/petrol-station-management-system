package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.entity.Receipt;
import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOFilter;
import com.gasstation.managementsystem.utils.QueryGenerateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;

@Repository
@RequiredArgsConstructor
public class ReceiptRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> findAll(ReceiptDTOFilter filter) {
        StringBuilder query = new StringBuilder("select r from Receipt r inner join r.card c inner join c.customer cus inner join r.creator cre where 1 = 1");
        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        qHelper.between("r.createdDate", filter.getCreatedDateFrom(), filter.getCreatedDateTo())
                .between("r.amount", filter.getAmountFrom(), filter.getAmountTo())
                .like("cus.name", "customerName", filter.getCustomerName())
                .like("cre.name", "creatorName", filter.getCreatorName())
                .like("r.reason", "reason", filter.getReason())
                .equal("r.transaction.pumpShift.pump.tank.station.owner.id", "ownerId", filter.getOwnerId());
        String countQuery = qHelper.getQuery().toString().replace("select r", "select count(r.id)");
        Query countTotalQuery = em.createQuery(countQuery);
        qHelper.sort("r.id", "DESC");
        TypedQuery<Receipt> tQuery = em.createQuery(query.toString(), Receipt.class);
        return qHelper.paging(tQuery, countTotalQuery, filter.getPageIndex(), filter.getPageSize());
    }
}
