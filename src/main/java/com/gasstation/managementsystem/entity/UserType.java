package com.gasstation.managementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_type_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UserType extends BaseEntity {
    public static final int ADMIN = 1;
    public static final int OWNER = 2;
    //    public static final int EMPLOYEE = 3;
    public static final int CUSTOMER = 4;
    //    public static final int SUPPLIER = 5;
    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "userType")
    private List<User> userList;//Dánh sách user có thuộc type này

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userTypeList")
    private Set<Api> apiList = new HashSet<>();// danh sách api của type này

}
