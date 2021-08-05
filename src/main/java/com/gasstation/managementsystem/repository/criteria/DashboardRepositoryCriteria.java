package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.model.FuelStatistic;
import com.gasstation.managementsystem.model.dto.dashboard.FuelStatisticDTOFilter;
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

    public HashMap<String, Object> fuelStatistic(FuelStatisticDTOFilter filter) {
        String str = "select tbl1.*, tbl2.totalPaid\n" +
                "from (SELECT ft.id                                           as fuel_id,\n" +
                "             ft.name                                         AS fuel_name,\n" +
                "             st.id                                           AS station_id,\n" +
                "             st.name                                         AS station_name,\n" +
                "             st.address                                      AS station_address,\n" +
                "             coalesce(sum(tran.volume * tran.unit_price), 0) as total_revenue,\n" +
                "             coalesce(sum(dt.accounts_payable), 0)           as total_debt,\n" +
                "             coalesce(sum(tran.volume), 0)                   as totalVolume\n" +
                "      FROM transaction_tbl tran\n" +
                "               right join debt_tbl dt on tran.id = dt.transaction_id\n" +
                "               RIGHT JOIN pump_shift_tbl pst ON pst.id = tran.pump_shift_id\n" +
                "               RIGHT JOIN pump_tbl pt ON pt.id = pst.pump_id\n" +
                "               RIGHT JOIN tank_tbl tt ON tt.id = pt.tank_id\n" +
                "               right join station_tbl st on st.id = tt.station_id\n" +
                "               RIGHT JOIN fuel_tbl ft ON ft.id = tt.fuel_id\n" +
                "      where tran.time between :startTime and :endTime\n" +
                "         or tran.time is null\n" +
                "      GROUP BY ft.id, ft.name, st.id, st.name, st.address) as tbl1,\n" +
                "     (\n" +
                "         select ft.id as fuel_id, coalesce(sum(tt.volume * tt.unit_price), 0) as totalPaid\n" +
                "         from card_tbl ct\n" +
                "                  right join transaction_tbl tt on ct.id = tt.card_id\n" +
                "                  right join pump_shift_tbl pst on pst.id = tt.pump_shift_id\n" +
                "                  right join pump_tbl pt on pt.id = pst.pump_id\n" +
                "                  right join tank_tbl t on t.id = pt.tank_id\n" +
                "                  right join fuel_tbl ft on ft.id = t.fuel_id\n" +
                "         where tt.time between :startTime and :endTime\n" +
                "            or tt.time is null\n" +
                "         GROUP BY ft.id)\n" +
                "         as tbl2\n" +
                "where tbl1.fuel_id = tbl2.fuel_id and tbl1.station_id is not null";
        if (filter.getStationId() != null) {
            str += "  and tbl1.station_id = :stationId";
        }
        Query nativeQuery = em.createNativeQuery(str);
        nativeQuery.setParameter("startTime", filter.getStartTime());
        nativeQuery.setParameter("endTime", filter.getEndTime());
        if (filter.getStationId() != null) {
            nativeQuery.setParameter("stationId", filter.getStationId());
        }

        List<Object[]> listResult = nativeQuery.getResultList();
        List<FuelStatistic> fuelStatistics = new ArrayList<>();
        listResult.forEach(objects -> {
            FuelStatistic revenue = FuelStatistic.builder()
                    .fuelId((Integer) objects[0])
                    .fuelName((String) objects[1])
                    .stationId((Integer) objects[2])
                    .stationName((String) objects[3])
                    .stationAddress((String) objects[4])
                    .totalRevenue((Double) objects[5])
                    .totalDebt((Double) objects[6])
                    .totalVolume((Double) objects[7])
                    .totalPaid((Double) objects[8])
                    .build();
            fuelStatistics.add(revenue);
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelStatistics);
        return map;
    }
}
