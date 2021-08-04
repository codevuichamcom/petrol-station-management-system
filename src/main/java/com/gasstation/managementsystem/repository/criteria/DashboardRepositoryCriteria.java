package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.model.Dashboard;
import com.gasstation.managementsystem.model.dto.dashboard.DashboardDTOFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DashboardRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> statistic(DashboardDTOFilter filter) {
        Query revenueTypedQuery = em.createNativeQuery("select ft.id as fuelId, ft.name as fuelName, coalesce(sum(tran.volume * tran.volume), 0) as revenue\n" +
                "from transaction_tbl tran\n" +
                "         right join hand_over_shift_tbl host on host.id = tran.hand_over_ship_id\n" +
                "         right join pump_tbl pt on pt.id = host.pump_id\n" +
                "         right join tank_tbl tt on tt.id = pt.tank_id\n" +
                "         right join fuel_tbl ft on ft.id = tt.fuel_id\n" +
                "where station_id = :stationId\n" +
                "group by ft.id, ft.name");
        revenueTypedQuery.setParameter("stationId", filter.getStationId());

        List<Object[]> listResult = revenueTypedQuery.getResultList();
        List<Dashboard> revenueList = new ArrayList<>();
        listResult.forEach(objects -> {
            Dashboard revenue = Dashboard.builder()
                    .fuelId((Integer) objects[0])
                    .fuelName((String) objects[1])
//                    .revenue((Double) objects[2])
                    .build();
            revenueList.add(revenue);
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", revenueList);
        return map;
    }
}
