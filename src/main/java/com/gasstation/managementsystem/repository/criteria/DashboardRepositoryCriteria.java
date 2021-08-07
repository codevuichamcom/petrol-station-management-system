package com.gasstation.managementsystem.repository.criteria;

import com.gasstation.managementsystem.model.FuelStatistic;
import com.gasstation.managementsystem.model.TankStatistic;
import com.gasstation.managementsystem.model.dto.dashboard.FuelStatisticDTOFilter;
import com.gasstation.managementsystem.model.dto.dashboard.TankStatisticDTOFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DashboardRepositoryCriteria {
    private final EntityManager em;

    public HashMap<String, Object> fuelStatistic(FuelStatisticDTOFilter filter) {
        String str = "select total_revenue_tbl.*,\n" +
                "       coalesce(total_debt_tbl.total_debt, 0) as total_debt,\n" +
                "       coalesce(total_paid_tbl.total_paid, 0) as total_paid\n" +
                "from (select ft.id                                           as fuel_id,\n" +
                "             ft.name                                         as fuel_name,\n" +
                "             st.id                                           as station_id,\n" +
                "             st.name                                         as station_nameo,\n" +
                "             coalesce(sum(tran.volume), 0)                   as total_volume,\n" +
                "             coalesce(sum(tran.volume * tran.unit_price), 0) as total_revenue\n" +
                "      from transaction_tbl tran\n" +
                "               inner join pump_shift_tbl pst on pst.id = tran.pump_shift_id\n" +
                "               inner join pump_tbl pt on pt.id = pst.pump_id\n" +
                "               inner join tank_tbl tt on tt.id = pt.tank_id\n" +
                "               inner join station_tbl st on st.id = tt.station_id\n" +
                "               inner join fuel_tbl ft on ft.id = tt.fuel_id\n" +
                "      where tran.time between :startTime and :endTime\n" +
                "      group by ft.id, st.id) as total_revenue_tbl\n" +
                "         left join\n" +
                "\n" +
                "     (select ft.id                    as fuel_id,\n" +
                "             st.id                    as station_id,\n" +
                "             sum(dt.accounts_payable) as total_debt\n" +
                "      from debt_tbl dt\n" +
                "               inner join transaction_tbl tt on tt.id = dt.transaction_id\n" +
                "               inner join pump_shift_tbl pst on pst.id = tt.pump_shift_id\n" +
                "               inner join pump_tbl pt on pt.id = pst.pump_id\n" +
                "               inner join tank_tbl t on t.id = pt.tank_id\n" +
                "               inner join station_tbl st on st.id = t.station_id\n" +
                "               inner join fuel_tbl ft on ft.id = t.fuel_id\n" +
                "      group by ft.id, st.id) as total_debt_tbl\n" +
                "     on total_revenue_tbl.fuel_id = total_debt_tbl.fuel_id\n" +
                "         and total_revenue_tbl.fuel_id = total_debt_tbl.fuel_id\n" +
                "         left join\n" +
                "     (select ft.id                                                                 as fuel_id,\n" +
                "             st.id                                                                 as station_id,\n" +
                "             sum((tt.volume * tt.unit_price) - coalesce((dt.accounts_payable), 0)) as total_paid\n" +
                "      from transaction_tbl tt\n" +
                "               left join debt_tbl dt on tt.id = dt.transaction_id\n" +
                "               inner join pump_shift_tbl pst on pst.id = tt.pump_shift_id\n" +
                "               inner join pump_tbl pt on pt.id = pst.pump_id\n" +
                "               inner join tank_tbl t on t.id = pt.tank_id\n" +
                "               inner join station_tbl st on st.id = t.station_id\n" +
                "               inner join fuel_tbl ft on ft.id = t.fuel_id\n" +
                "      where tt.card_id is not null\n" +
                "      group by ft.id, st.id) as total_paid_tbl\n" +
                "     on total_debt_tbl.fuel_id = total_paid_tbl.fuel_id\n" +
                "         and total_debt_tbl.station_id = total_paid_tbl.station_id\n";
        if (filter.getStationIds() != null && filter.getStationIds().length > 0) {
            str += "  where total_revenue_tbl.station_id in (:stationIds)";
        }
        Query nativeQuery = em.createNativeQuery(str);
        nativeQuery.setParameter("startTime", filter.getStartTime());
        nativeQuery.setParameter("endTime", filter.getEndTime());
        if (filter.getStationIds() != null && filter.getStationIds().length > 0) {
            nativeQuery.setParameter("stationIds", Arrays.asList(filter.getStationIds()));
        }

        List<Object[]> listResult = nativeQuery.getResultList();
        List<FuelStatistic> fuelStatistics = new ArrayList<>();
        listResult.forEach(objects -> {
            FuelStatistic fuelStatistic = FuelStatistic.builder()
                    .fuelId((Integer) objects[0])
                    .fuelName((String) objects[1])
                    .stationId((Integer) objects[2])
                    .stationName((String) objects[3])
                    .totalVolume((Double) objects[4])
                    .totalRevenue((Double) objects[5])
                    .totalDebt((Double) objects[6])
                    .totalPaid((Double) objects[7])
                    .build();
            fuelStatistics.add(fuelStatistic);
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelStatistics);
        return map;
    }

    public HashMap<String, Object> tankStatistic(TankStatisticDTOFilter filter) {
        String str = "select tn.tank_id as tank_id,\n" +
                "       tank_name,\n" +
                "       remain     as tank_remain,\n" +
                "       fuel_id,\n" +
                "       fuel_name,\n" +
                "       total_import,\n" +
                "       total_export\n" +
                "from (select tt.id                        as tank_id,\n" +
                "             tt.remain                    as remain,\n" +
                "             coalesce(sum(fit.volume), 0) as total_import\n" +
                "      from fuel_import_tbl fit\n" +
                "               right join tank_tbl tt on tt.id = fit.tank_id\n" +
                "      where fit.created_date between :startTime and :endTime\n" +
                "         or fit.created_date is null\n" +
                "      group by tt.id) as tn,\n" +
                "     (select tt.id                         as tank_id,\n" +
                "             tt.name                       as tank_name,\n" +
                "             ft.id                         as fuel_id,\n" +
                "             ft.name                       as fuel_name,\n" +
                "             coalesce(sum(tran.volume), 0) as total_export\n" +
                "      from transaction_tbl tran\n" +
                "               right join pump_shift_tbl pst on pst.id = tran.pump_shift_id\n" +
                "               right join pump_tbl pt on pt.id = pst.pump_id\n" +
                "               right join tank_tbl tt on tt.id = pt.tank_id\n" +
                "               right join fuel_tbl ft on ft.id = tt.fuel_id\n" +
                "      where tran.time between :startTime and :endTime\n" +
                "         or tran.time is null\n" +
                "      group by tt.id, tt.name, ft.id, ft.name\n" +
                "      having tt.id is not null) as tx\n" +
                "where tx.tank_id = tn.tank_id";

        Query nativeQuery = em.createNativeQuery(str);
        nativeQuery.setParameter("startTime", filter.getStartTime());
        nativeQuery.setParameter("endTime", filter.getEndTime());


        List<Object[]> listResult = nativeQuery.getResultList();
        List<TankStatistic> tankStatistics = new ArrayList<>();
        listResult.forEach(objects -> {
            TankStatistic revenue = TankStatistic.builder()
                    .tankId((Integer) objects[0])
                    .tankName((String) objects[1])
                    .tankRemain((Double) objects[2])
                    .fuelId((Integer) objects[3])
                    .fuelName((String) objects[4])
                    .totalImport((Double) objects[5])
                    .totalExport((Double) objects[6])
                    .build();
            tankStatistics.add(revenue);
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", tankStatistics);
        return map;
    }
}
