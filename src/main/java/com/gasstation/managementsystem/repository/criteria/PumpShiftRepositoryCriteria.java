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
        StringBuilder query = new StringBuilder("select ps from PumpShift ps inner join ps.pump p inner join p.tank t inner join ps.shift s where 1=1 ");
        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        String[] statuses = filter.getStatuses();
        Map<String, String> statusMap = toMap(statuses);

        qHelper.between("ps.createdDate", filter.getCreatedDateFrom(), filter.getCreatedDateTo())
                .in("t.station.id", "stationIds", filter.getStationIds())
                .like("t.station.name", "stationName", filter.getStationName())
                .like("ps.shift.name", "shiftName", filter.getShiftName())
                .like("p.name", "pumpName", filter.getPumpName());
        if (statuses == null || statuses.length == 0) {
            qHelper.between("ps.closedTime", filter.getClosedTimeFrom(), filter.getClosedTimeTo());
        } else {
            String closedCondition = "ps.closedTime IS NOT NULL";
            String unClosedCondition = "(ps.closedTime IS NULL AND (ps.createdDate+s.endTime) < (:today))";
            String workingCondition = "(ps.closedTime IS NULL AND (:today) BETWEEN (ps.createdDate+s.startTime) AND (ps.createdDate+s.endTime))";
            String futureCondition = "(ps.closedTime IS NULL AND (:today) < (ps.createdDate+s.startTime))";
            boolean isOr = false;
            Long today = DateTimeHelper.getCurrentUnixTime();
            if (statusMap.containsKey(PumpShiftDTOFilter.STATUS_CLOSED)) {
                qHelper.and().openBracket().getQuery().append(closedCondition);
                isOr = true;
            }
            if (statusMap.containsKey(PumpShiftDTOFilter.STATUS_UNCLOSE)) {
                if (isOr) {
                    qHelper.or();
                } else {
                    qHelper.and().openBracket();
                    isOr = true;
                }
                qHelper.getQuery().append(unClosedCondition);
                if (!qHelper.getParams().containsKey("today")) {
                    qHelper.getParams().put("today", today);
                }
            }
            if (statusMap.containsKey(PumpShiftDTOFilter.STATUS_WORKING)) {
                if (isOr) {
                    qHelper.or();
                } else {
                    qHelper.and().openBracket();
                    isOr = true;
                }
                qHelper.getQuery().append(workingCondition);
                if (!qHelper.getParams().containsKey("today")) {
                    qHelper.getParams().put("today", today);
                }
            }
            if (statusMap.containsKey(PumpShiftDTOFilter.STATUS_FUTURE)) {
                if (isOr) {
                    qHelper.or();
                } else {
                    qHelper.and().openBracket();
                    isOr = true;
                }
                qHelper.getQuery().append(futureCondition);
                if (!qHelper.getParams().containsKey("today")) {
                    qHelper.getParams().put("today", today);
                }
            }
            if (isOr) {
                qHelper.closeBracket();
            }
        }
        String countQuery = qHelper.getQuery().toString().replace("select ps", "select count(ps.id)");
        Query countTotalQuery = em.createQuery(countQuery);
        String totalVolumeAndAmountQuery = qHelper.getQuery().toString().replace("select ps from PumpShift", "select coalesce(sum(tran.volume), 0), coalesce(sum(tran.totalAmount), 0) from Transaction tran inner join tran.pumpShift");
        QueryGenerateHelper volumeAmountHelper = new QueryGenerateHelper();
        volumeAmountHelper.setQuery(new StringBuilder(totalVolumeAndAmountQuery));
        volumeAmountHelper.setParams(qHelper.getParams());
        Query volumeAmountQuery = em.createQuery(totalVolumeAndAmountQuery);
        volumeAmountHelper.setValueToParams(volumeAmountQuery);
        Object[] volumeAndAmount = (Object[]) volumeAmountQuery.getSingleResult();
        qHelper.sort("ps.id", "DESC");
        TypedQuery<PumpShift> tQuery = em.createQuery(qHelper.getQuery().toString(), PumpShift.class);
        HashMap<String, Object> map = qHelper.paging(tQuery, countTotalQuery, filter.getPageIndex(), filter.getPageSize());
        map.put("totalVolume", volumeAndAmount[0]);
        map.put("totalAmount", volumeAndAmount[1]);
        return map;
    }

    private Map<String, String> toMap(String[] statuses) {
        Map<String, String> map = new HashMap<>();
        if (statuses != null && statuses.length != 0) {
            for (String status : statuses) {
                status = status.trim().toUpperCase();
                switch (status) {
                    case PumpShiftDTOFilter.STATUS_UNCLOSE:
                    case PumpShiftDTOFilter.STATUS_CLOSED:
                    case PumpShiftDTOFilter.STATUS_WORKING:
                    case PumpShiftDTOFilter.STATUS_FUTURE:
                        map.put(status, status);
                        break;
                    default:
                        break;
                }
            }
        }
        return map;
    }

    public PumpShift getPumpShiftToday() {
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
