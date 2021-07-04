package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByPhone(String phone);

    Optional<Employee> findByIdentityCardNumber(String identityCardNumber);

    @Query("select e from Employee e inner join e.station s where s.owner.id=?1")
    List<Employee> findAllByOwnerId(int ownerId, Sort sort);
}
