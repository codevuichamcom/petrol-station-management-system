package com.gasstation.managementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gasstation.managementsystem.model.dto.AccountDTO;
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
    private String role;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    public Account(AccountDTO accountDTO) {
        this.id = accountDTO.getId();
        this.username = accountDTO.getUsername();
        this.role = accountDTO.getRole();
        this.user = accountDTO.getUser();
        this.userType = accountDTO.getUserType();
    }
}
