package com.gasstation.managementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "api_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"method", "path"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Api extends BaseEntity {
    public static final String PREFIX = "/api/v1";

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private String path;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_tbl", joinColumns = @JoinColumn(name = "api_id"),
            inverseJoinColumns = @JoinColumn(name = "user_type_id"))
    private Set<UserType> userTypeList = new HashSet<>();//Danh sách userType có quyền truy cập api này


}
