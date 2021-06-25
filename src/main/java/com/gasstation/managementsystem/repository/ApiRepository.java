package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiRepository extends JpaRepository<Api, Integer> {
    Optional<Api> findByApiAndMethod(String api, String method);
}
