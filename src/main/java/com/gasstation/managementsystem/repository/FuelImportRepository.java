package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.FuelImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelImportRepository extends JpaRepository<FuelImport, Integer> {
}
