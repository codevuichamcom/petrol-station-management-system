package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    public Page<Station> findByOwnerId(int ownerId, Pageable pageable);
    public List<Station> findByOwnerId(int ownerId);
}
