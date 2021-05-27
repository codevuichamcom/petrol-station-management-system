package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Tank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TankRepository extends JpaRepository<Tank, Integer> {
}
