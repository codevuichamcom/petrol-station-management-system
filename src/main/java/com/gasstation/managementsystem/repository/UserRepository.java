package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findByUserTypeId(int typeId);

    User findByIdentityCardNumber(String identityCardNumber);

    User findByPhone(String phone);

    User findByEmail(String email);
}
