package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "account_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userInfo;


//    public Account(AccountDTO accountDTO) {
//        this.id = accountDTO.getId();
//        this.username = accountDTO.getUsername();
//        this.role = accountDTO.getRole();
//        this.user = accountDTO.getUser();
//        this.userRole = accountDTO.getUserRole();
//    }
}
