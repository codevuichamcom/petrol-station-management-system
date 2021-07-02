package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByPhone(String phone);

    Optional<Employee> findByIdentityCardNumber(String identityCardNumber);
}
