package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.entity.HandOverShift;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOFilter;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.QueryGenerateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;

@Repository
@RequiredArgsConstructor
public class HandOverShiftRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> findAll(HandOverShiftDTOFilter filter) {
        StringBuilder query = new StringBuilder("select h from HandOverShift h inner join h.pump p inner join p.tank t where 1=1 ");
        QueryGenerateHelper qHelper = new QueryGenerateHelper();
        qHelper.setQuery(query);
        qHelper.between("h.createdDate", 0L, filter.getCreatedDate(), "createdDate", filter.getCreatedDate())
                .between("h.closedTime", 0L, filter.getClosedTime(), "closedTime", filter.getClosedTime())
                .in("t.station.id", "stationIds", filter.getStationIds())
                .in("h.shift.id", "shiftIds", filter.getShiftIds())
                .in("p.id", "pumpIds", filter.getPumpIds())
                .like("h.actor.name", "actorName", filter.getActorName());
        String countQuery = qHelper.getQuery().toString().replace("select h", "select count(h.id)");
        Query countTotalQuery = em.createQuery(countQuery);
        qHelper.sort("h.createdDate", "DESC");
        TypedQuery<HandOverShift> tQuery = em.createQuery(qHelper.getQuery().toString(), HandOverShift.class);
        return qHelper.paging(tQuery, countTotalQuery, filter.getPageIndex(), filter.getPageSize());
    }

    public HandOverShift getHandOverShiftToday() {
        TypedQuery<HandOverShift> tQuery = em.createQuery("select h from HandOverShift  h where  h.createdDate = :today", HandOverShift.class);
        tQuery.setParameter("today", DateTimeHelper.getCurrentDate());
        tQuery.setMaxResults(1);
        HandOverShift handOverShift = null;
        try {
            handOverShift = tQuery.getSingleResult();
        } catch (NoResultException nre) {
            //Ignore this because as per your logic this is ok!
        }
        return handOverShift;
    }
}
