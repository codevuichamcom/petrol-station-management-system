package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    @Query("select s from Shift s where s.station.id=?1")
    List<Shift> findAllShiftByStationId(int stationId);
}
