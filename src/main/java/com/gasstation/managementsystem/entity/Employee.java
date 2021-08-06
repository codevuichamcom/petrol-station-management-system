package com.gasstation.managementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Employee extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private Boolean gender = false;

    @Column(nullable = false)
    private Long dateOfBirth;

    @Column(nullable = false, unique = true)
    private String identityCardNumber;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station; //nhân viên này thuộc trạm nào

    @OneToMany(mappedBy = "employee")
    private List<WorkSchedule> workScheduleList = new ArrayList<>();
}
