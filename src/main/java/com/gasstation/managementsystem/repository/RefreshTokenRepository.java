package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.RefreshToken;
import com.gasstation.managementsystem.entity.Tank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
