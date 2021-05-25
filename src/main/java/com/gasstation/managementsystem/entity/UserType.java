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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;

    @OneToMany(mappedBy = "userType",cascade = CascadeType.ALL)
    private List<Account> accounts;
}
