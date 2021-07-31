package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private Boolean gender = true;

    @Column(nullable = false)
    private long dateOfBirth;

    @Column(nullable = false, unique = true)
    private String identityCardNumber;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station; //nhân viên này thuộc trạm nào

    @OneToMany(mappedBy = "employee")
    private List<WorkSchedule> workScheduleList = new ArrayList<>();
}
