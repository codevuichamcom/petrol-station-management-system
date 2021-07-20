package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.entity.HandOverShift;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOFilter;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashMap;

@Repository
@RequiredArgsConstructor
public class HandOverShiftRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> findAll(HandOverShiftDTOFilter filter) {
        return null;
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
