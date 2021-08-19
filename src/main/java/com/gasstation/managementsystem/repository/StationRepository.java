package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Station;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    List<Station> findByOwnerId(int ownerId, Sort sort);

    @Query("select s from Station  s where s.name like ?1 and s.address like ?2")
    Optional<Station> findByNameAndAddress(String name, String address);
}
