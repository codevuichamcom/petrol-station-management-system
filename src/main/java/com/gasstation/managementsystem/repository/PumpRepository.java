package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Pump;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PumpRepository extends JpaRepository<Pump, Integer> {
    @Query("select p from Pump p inner join  p.tank t inner join t.station s where s.owner.id=?1")
    List<Pump> findAllByOwnerId(int ownerId, Sort sort);

    Optional<Pump> findByNameAndTankId(String name, int tankId);
}
