package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.FuelImport;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FuelImportRepository extends JpaRepository<FuelImport, Integer> {
    @Query(value = "select f from FuelImport f inner join f.tank t where t.station.owner.id = ?1")
    List<FuelImport> findAllByOwnerId(int ownerId, Sort sort);
}
