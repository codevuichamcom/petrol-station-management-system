package com.gasstation.managementsystem.entity;

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
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userInfo;


//    public Account(AccountDTO accountDTO) {
//        this.id = accountDTO.getId();
//        this.username = accountDTO.getUsername();
//        this.password = accountDTO.getPassword();
//    }
}
