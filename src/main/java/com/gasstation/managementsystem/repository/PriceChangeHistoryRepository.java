package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceChangeHistoryRepository extends JpaRepository<PriceChangeHistory, Integer> {
    @Query(value = "select p from PriceChangeHistory p where p.tank.id=?1",
            countQuery = "select count (p) from PriceChangeHistory p where p.tank.id=?1")
    Page<PriceChangeHistory> findAllByTankId(int tankId, Pageable pageable);
}
