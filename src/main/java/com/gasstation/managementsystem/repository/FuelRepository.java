package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Integer> {
    Optional<Fuel> findByNameContainingIgnoreCase(String name);
}
