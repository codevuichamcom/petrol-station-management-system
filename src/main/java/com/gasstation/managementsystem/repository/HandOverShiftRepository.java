package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.HandOverShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandOverShiftRepository extends JpaRepository<HandOverShift, Integer> {
}
