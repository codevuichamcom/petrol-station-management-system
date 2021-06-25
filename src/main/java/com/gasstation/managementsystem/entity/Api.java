package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "api_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"method", "api"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String method;

    private String api;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "permission_tbl", joinColumns = @JoinColumn(name = "api_id"),
            inverseJoinColumns = @JoinColumn(name = "user_type_id"))
    private Set<UserType> userTypeList = new HashSet<>();//Danh sách userType có quyền truy cập api này


}
