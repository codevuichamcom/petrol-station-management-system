package com.gasstation.managementsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private int id;
    private String username;
    private String role;
    private User user;
    private UserType userType;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.role = account.getRole();
        this.user = account.getUser();
        this.userType = account.getUserType();
    }
}
