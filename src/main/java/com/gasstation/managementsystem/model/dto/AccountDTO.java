package com.gasstation.managementsystem.model.dto;

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
    private UserDTO userInfo;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.userInfo = new UserDTO(account.getUserInfo());
    }
}
