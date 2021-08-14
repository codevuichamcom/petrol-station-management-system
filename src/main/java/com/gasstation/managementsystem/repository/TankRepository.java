package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Tank;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TankRepository extends JpaRepository<Tank, Integer> {
    @Query("select t from Tank t where t.name=?1 and t.station.id=?2")
    Optional<Tank> findByNameAndStationId(String name, int stationId);

    @Query("select t from Tank t where t.station.owner.id = ?1")
    List<Tank> findAllByOwnerId(int ownerId, Sort sort);
}
