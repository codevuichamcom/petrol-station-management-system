package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.HandOverShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

@Repository
public interface HandOverShiftRepository extends JpaRepository<HandOverShift, Integer> {
    @Query("select h from HandOverShift h inner join h.shift s where h.pump.id=?1 and h.createdDate=?2 and ?3 between s.startTime and s.endTime and h.closeShiftDate IS NULL")
    Optional<HandOverShift> findByPumpIdNotClose(int pumpId, Date date, Time time);
}
