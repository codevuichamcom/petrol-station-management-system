package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByDriverPhone(String driverPhone);
}
