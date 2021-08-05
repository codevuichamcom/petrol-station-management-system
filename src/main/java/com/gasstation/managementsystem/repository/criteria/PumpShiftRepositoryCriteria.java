package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.entity.PumpShift;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTOFilter;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.QueryGenerateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PumpShiftRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> findAll(PumpShiftDTOFilter filter) {
        StringBuilder query = new StringBuilder("select ps from PumpShift ps inner join ps.pump p inner join p.tank t where 1=1 ");
        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        String[] statuses = filter.getStatuses();
        Map<String, String> statusMap = toMap(statuses);

        qHelper.between("ps.createdDate", 0L, filter.getCreatedDate(), "createdDate", filter.getCreatedDate())
                .in("t.station.id", "stationIds", filter.getStationIds())
                .in("ps.shift.id", "shiftIds", filter.getShiftIds())
                .in("p.id", "pumpIds", filter.getPumpIds())
                .like("ps.executor.name", "executorName", filter.getExecutorName());
        boolean isOr = false;
        if (statuses == null || statuses.length == 0) {
            qHelper.between("ps.closedTime", 0L, filter.getClosedTime(), "closedTime", filter.getClosedTime());
        } else {
            if (statusMap.containsKey(PumpShiftDTOFilter.STATUS_UNCLOSE)) {
                qHelper.and().openBracket().isNULL("ps.closedTime");
                isOr = true;
            }
            if (statusMap.containsKey(PumpShiftDTOFilter.STATUS_CLOSED)) {
                if (isOr) {
                    qHelper.or();
                } else {
                    qHelper.and();
                }
                qHelper.openBracket()
                        .isNotNULL("ps.closedTime")
                        .between("ps.closedTime", 0L, filter.getClosedTime(), "closedTime", filter.getClosedTime())
                        .closeBracket();
            }
            if (isOr) {
                qHelper.closeBracket();
            }
        }


        String countQuery = qHelper.getQuery().toString().replace("select ps", "select count(ps.id)");
        Query countTotalQuery = em.createQuery(countQuery);
        qHelper.sort("ps.createdDate", "DESC");
        TypedQuery<PumpShift> tQuery = em.createQuery(qHelper.getQuery().toString(), PumpShift.class);
        return qHelper.paging(tQuery, countTotalQuery, filter.getPageIndex(), filter.getPageSize());
    }

    private Map<String, String> toMap(String[] statuses) {
        Map<String, String> map = new HashMap<>();
        if (statuses != null && statuses.length != 0) {
            for (String status : statuses) {
                if (status.trim().equalsIgnoreCase(PumpShiftDTOFilter.STATUS_UNCLOSE)) {
                    map.put(PumpShiftDTOFilter.STATUS_UNCLOSE, "UNCLOSE");
                }
                if (status.trim().equalsIgnoreCase(PumpShiftDTOFilter.STATUS_CLOSED)) {
                    map.put(PumpShiftDTOFilter.STATUS_CLOSED, "CLOSED");
                }
            }
        }
        return map;
    }

    public PumpShift getHandOverShiftToday() {
        TypedQuery<PumpShift> tQuery = em.createQuery("select ps from PumpShift ps where  ps.createdDate = :today", PumpShift.class);
        tQuery.setParameter("today", DateTimeHelper.getCurrentDate());
        tQuery.setMaxResults(1);
        PumpShift pumpShift = null;
        try {
            pumpShift = tQuery.getSingleResult();
        } catch (NoResultException nre) {
            //Ignore this because as per your logic this is ok!
        }
        return pumpShift;
    }
}
