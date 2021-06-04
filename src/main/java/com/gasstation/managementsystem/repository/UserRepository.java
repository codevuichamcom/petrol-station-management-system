package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u inner join u.account as a where a.username=?1")
    public User findByUsername(String username);

    public List<User> findByUserTypeId(int typeId);
}
