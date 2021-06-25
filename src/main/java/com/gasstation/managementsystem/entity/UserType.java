package com.gasstation.managementsystem.entity;

import lombok.*;

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
@Builder
public class UserType {
    public static final int ADMIN = 1;
    public static final int OWNER = 2;
    public static final int EMPLOYEE = 3;
    public static final int CUSTOMER = 4;
    public static final int SUPPLIER = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "userType", cascade = CascadeType.ALL)
    private List<User> userList;//Dánh sách user có thuộc type này

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userTypeList")
    private Set<Api> apiList = new HashSet<>();// danh sách api của type này

}
