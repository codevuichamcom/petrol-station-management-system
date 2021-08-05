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
        Query nativeQuery = em.createNativeQuery("SELECT ft.id                                                                                     as fuel_id,\n" +
                "       ft.name                                                                                   AS fuel_name,\n" +
                "       st.id                                                                                     AS station_id,\n" +
                "       st.name                                                                                   AS station_name,\n" +
                "       st.address                                                                                AS station_address,\n" +
                "       coalesce(sum(tran.volume * tran.unit_price), 0)                                           as total_revenue,\n" +
                "       coalesce(sum(dt.accounts_payable), 0)                                                     as total_debt,\n" +
                "       (coalesce(sum(tran.volume * tran.unit_price), 0) - coalesce(sum(dt.accounts_payable), 0)) as total_money\n" +
                "FROM transaction_tbl tran\n" +
                "         right join debt_tbl dt on tran.id = dt.transaction_id\n" +
                "         RIGHT JOIN pump_shift_tbl pst ON pst.id = tran.pump_shift_id\n" +
                "         RIGHT JOIN pump_tbl pt ON pt.id = pst.pump_id\n" +
                "         RIGHT JOIN tank_tbl tt ON tt.id = pt.tank_id\n" +
                "         right join station_tbl st on st.id = tt.station_id\n" +
                "         RIGHT JOIN fuel_tbl ft ON ft.id = tt.fuel_id\n" +
                "where tran.time between :startTime and :endTime\n" +
                "   or tran.time is null\n" +
                "GROUP BY ft.id, ft.name, st.id, st.name, st.address\n" +
                "having st.id = :stationId");
        nativeQuery.setParameter("startTime", filter.getStartTime());
        nativeQuery.setParameter("endTime", filter.getEndTime());
        nativeQuery.setParameter("stationId", filter.getStationId());

        List<Object[]> listResult = nativeQuery.getResultList();
        List<Dashboard> dashboards = new ArrayList<>();
        listResult.forEach(objects -> {
            Dashboard revenue = Dashboard.builder()
                    .fuelId((Integer) objects[0])
                    .fuelName((String) objects[1])
                    .stationId((Integer) objects[2])
                    .stationName((String) objects[3])
                    .stationAddress((String) objects[4])
                    .totalRevenue((Double) objects[5])
                    .totalDebt((Double) objects[6])
                    .totalMoney((Double) objects[7])
                    .build();
            dashboards.add(revenue);
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", dashboards);
        return map;
    }
}
