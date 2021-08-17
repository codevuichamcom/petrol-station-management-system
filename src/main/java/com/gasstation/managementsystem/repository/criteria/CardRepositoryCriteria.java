package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.model.dto.card.CardDTOFilter;
import com.gasstation.managementsystem.utils.QueryGenerateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CardRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> findAll(CardDTOFilter filter) {
        StringBuilder query = new StringBuilder("select c from Card c left outer join c.customer cus where 1=1");
        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        String[] statuses = filter.getStatuses();
        Map<String, String> statusMap = toMap(statuses);

        qHelper.between("c.accountsPayable", filter.getAccountsPayableFrom(), filter.getAccountsPayableTo())
                .between("c.availableBalance", filter.getAvailableBalanceFrom(), filter.getAvailableBalanceTo())
                .like("cus.name", "customerName", filter.getCustomerName())
                .equal("cus.id", "customerId", filter.getCustomerId());
        boolean isOr = false;
        if (statusMap.containsKey(CardDTOFilter.STATUS_ACTIVATED)) {
            qHelper.and().openBracket();
            qHelper.getQuery().append("c.active = true");
            isOr = true;
        }
        if (statusMap.containsKey(CardDTOFilter.STATUS_DEACTIVATED)) {
            if (isOr) {
                qHelper.or();
                qHelper.getQuery().append("c.active = false");
            } else {
                qHelper.and().getQuery().append("c.active = false");
            }
        }
        if (isOr) {
            qHelper.closeBracket();
        }


        String countQuery = qHelper.getQuery().toString().replace("select c", "select count(c.id)");
        Query countTotalQuery = em.createQuery(countQuery);
        qHelper.sort("c.createdDate", "DESC");
        TypedQuery<Card> tQuery = em.createQuery(qHelper.getQuery().toString(), Card.class);
        return qHelper.paging(tQuery, countTotalQuery, filter.getPageIndex(), filter.getPageSize());
    }

    private Map<String, String> toMap(String[] statuses) {
        Map<String, String> map = new HashMap<>();
        if (statuses != null && statuses.length != 0) {
            for (String status : statuses) {
                if (status.trim().equalsIgnoreCase(CardDTOFilter.STATUS_ACTIVATED)) {
                    map.put(CardDTOFilter.STATUS_ACTIVATED, CardDTOFilter.STATUS_ACTIVATED);
                }
                if (status.trim().equalsIgnoreCase(CardDTOFilter.STATUS_DEACTIVATED)) {
                    map.put(CardDTOFilter.STATUS_DEACTIVATED, CardDTOFilter.STATUS_DEACTIVATED);
                }
            }
        }
        return map;
    }
}
