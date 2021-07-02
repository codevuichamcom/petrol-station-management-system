package com.gasstation.managementsystem.repository;

import com.gasstation.managementsystem.entity.WorkSchedule;
import com.gasstation.managementsystem.entity.primaryCombine.WorkSchedulePrimary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, WorkSchedulePrimary> {
}
