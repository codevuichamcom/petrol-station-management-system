package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.HandOverShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HandOverShiftRepository extends JpaRepository<HandOverShift, Integer> {
    @Query("select h from HandOverShift h inner join h.shift s where h.pump.id=?1 and ?2-h.createdDate<86400000 and ?3 between s.startTime and s.endTime and h.closedTime IS NULL")
    Optional<HandOverShift> findByPumpIdNotClose(int pumpId, long createdDate, long seconds);

    @Query("select  h from  HandOverShift h inner join h.pump p inner join p.tank t where t.station.id=?1 and h.closedTime IS NULL")
    List<HandOverShift> findAllByStationId(int stationId);
}
