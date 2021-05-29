package com.gasstation.managementsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AccountDTO {
    private int id;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private UserDTO userInfo;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
//        this.userInfo = new UserDTO(account.getUserInfo());
    }
}
