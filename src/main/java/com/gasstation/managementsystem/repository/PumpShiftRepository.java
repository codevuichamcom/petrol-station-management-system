package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.PumpShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PumpShiftRepository extends JpaRepository<PumpShift, Integer> {
    @Query("select h from PumpShift h inner join h.shift s where h.pump.id=?1 and ?2-h.createdDate<86400000 and ?3 between s.startTime and s.endTime and h.closedTime IS NULL")
    Optional<PumpShift> findByPumpIdNotClose(int pumpId, long createdDate, long milliSeconds);

    @Query("select  ps from  PumpShift ps inner join ps.pump p inner join p.tank t where t.station.id=?1 and (ps.closedTime IS NULL AND (ps.createdDate + ps.shift.endTime) < (?2))")
    List<PumpShift> findAllByStationId(int stationId, long currentTime);
}
