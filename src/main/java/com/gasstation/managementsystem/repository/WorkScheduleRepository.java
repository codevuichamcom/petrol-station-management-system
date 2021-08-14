package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.WorkSchedule;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Integer> {
    @Query("select w from WorkSchedule w where w.shift.station.owner.id = ?1")
    List<WorkSchedule> findAllByOwnerId(int ownerId, Sort sort);
}
