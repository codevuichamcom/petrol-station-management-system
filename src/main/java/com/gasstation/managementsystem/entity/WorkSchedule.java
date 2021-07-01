package com.gasstation.managementsystem.entity;

import com.gasstation.managementsystem.entity.primaryCombine.WorkSchedulePrimary;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work_schedule_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkSchedule {
    @EmbeddedId
    private WorkSchedulePrimary id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("shiftId")
    private Shift shift;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;
}
