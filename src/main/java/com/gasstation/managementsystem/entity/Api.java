package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "api_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"method", "path"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Api {
    public static final String PREFIX ="/api/v1";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private String path;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "permission_tbl", joinColumns = @JoinColumn(name = "api_id"),
            inverseJoinColumns = @JoinColumn(name = "user_type_id"))
    private Set<UserType> userTypeList = new HashSet<>();//Danh sách userType có quyền truy cập api này


}
