package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Shift;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    @Query("select s from Shift s where s.station.id=?1")
    List<Shift> findAllShiftByStationId(int stationId);

    @Query("select s from Shift s where s.station.owner.id=?1")
    List<Shift> findAllShiftByOwnerId(int ownerId, Sort sort);

    @Query("select s from Shift s where s.name=?1 and s.station.id=?2")
    Optional<Shift> findByNameAndStationId(String name, int stationId);
}
