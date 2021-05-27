package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceChangeHistoryRepository extends JpaRepository<PriceChangeHistory, Integer> {
}
