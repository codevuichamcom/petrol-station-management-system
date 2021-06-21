package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "userType", cascade = CascadeType.ALL)
    private List<User> userList;//Dánh sách user có thuộc type này

    @ManyToMany(mappedBy = "userTypeList")
    private List<Api> apiList;// danh sách api của type này

}
