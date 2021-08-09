package com.gasstation.managementsystem.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "work_schedule_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class WorkSchedule extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @Column(nullable = false)
    private Long startDate;

    @Column(nullable = false)
    private Long endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkSchedule that = (WorkSchedule) o;
        return Objects.equals(id, that.id) && Objects.equals(employee, that.employee) && Objects.equals(shift, that.shift) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }
}
