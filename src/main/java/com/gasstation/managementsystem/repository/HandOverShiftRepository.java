package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.HandOverShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HandOverShiftRepository extends JpaRepository<HandOverShift, Integer> {
    @Query("select h from HandOverShift h where h.pump.id=?1 and h.closeShiftDate IS NULL")
    Optional<HandOverShift> findByPumpIdNotClose(int pumpId);
}
