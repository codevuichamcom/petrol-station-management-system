package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.entity.Debt;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOFilter;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOSummary;
import com.gasstation.managementsystem.model.dto.debt.DebtDTOSummaryFilter;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.utils.QueryGenerateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DebtRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> getDetail(DebtDTOFilter filter) {
        StringBuilder query = new StringBuilder("select d from Debt d where 1=1");
        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        qHelper.equal("d.transaction.card.id", "cardId", filter.getCardId())
                .equal("d.transaction.pumpShift.pump.tank.station.id", "stationId", filter.getStationId());

        TypedQuery<Debt> tQuery = em.createQuery(query.toString(), Debt.class);
        qHelper.setValueToParams(tQuery);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", tQuery.getResultList());
        return map;
    }

    public HashMap<String, Object> summary(DebtDTOSummaryFilter filter) {
        StringBuilder query = new StringBuilder("select * from debt_summary ds where 1=1 ");
        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        qHelper.like("ds.card_id", "cardIds", filter.getCardId())
                .equal("ds.station_id", "stationId", filter.getStationId())
                .like("ds.station_name", "stationName", filter.getStationName())
                .like("ds.customer_name", "customerName", filter.getCustomerName())
                .between("ds.total_accounts_payable", filter.getTotalAccountsPayableFrom(), filter.getTotalAccountsPayableTo())
                .in("ds.station_id", "stationIds", filter.getStationIds())
                .equal("ds.customer_id", "customerId", filter.getCustomerId());

        Query queryExecutor = em.createNativeQuery(query.toString());
        String countQuery = qHelper.getQuery().toString().replace("*", "count(*)");
        Query countTotalQuery = em.createNativeQuery(countQuery);
        qHelper.setValueToParams(queryExecutor).setValueToParams(countTotalQuery);

        long totalElement = ((BigInteger) countTotalQuery.getSingleResult()).longValue();

        List<DebtDTOSummary> debtDTOSummaryList = mapToListDebtDTOSummary(queryExecutor, filter.getPageIndex(), filter.getPageSize());
        return qHelper.paging(filter.getPageSize(), totalElement, debtDTOSummaryList);
    }

    private List<DebtDTOSummary> mapToListDebtDTOSummary(Query queryExecutor, Integer pageIndex, Integer pageSize) {
        queryExecutor.setFirstResult((pageIndex - 1) * pageSize);
        queryExecutor.setMaxResults(pageSize);
        List<DebtDTOSummary> debtDTOSummaryList = new ArrayList<>();
        List<Object[]> listResult = queryExecutor.getResultList();
        listResult.forEach(objects -> {
            DebtDTOSummary debtDTOSummary = DebtDTOSummary.builder()
                    .card(CardDTO.builder()
                            .id(UUID.fromString((String) objects[0]))
                            .customer(UserDTO.builder()
                                    .id((Integer) objects[4])
                                    .name((String) objects[5])
                                    .phone((String) objects[6]).build()).build())
                    .station(StationDTO.builder()
                            .id((Integer) objects[1])
                            .name((String) objects[2])
                            .address((String) objects[3]).build())
                    .totalAccountsPayable((Double) objects[7])
                    .build();
            debtDTOSummaryList.add(debtDTOSummary);
        });
        return debtDTOSummaryList;
    }
}
