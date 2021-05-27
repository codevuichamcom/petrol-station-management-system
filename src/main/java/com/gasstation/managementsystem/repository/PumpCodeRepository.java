package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.PumpCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PumpCodeRepository extends JpaRepository<PumpCode, Integer> {
}
