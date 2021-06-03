package com.gasstation.managementsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gasstation.managementsystem.entity.Account;
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
    private UserDTO userInfo;

    public AccountDTO(Account account) {
        if (account != null) {
            this.id = account.getId();
            this.username = account.getUsername();
            this.password = account.getPassword();
        }
    }
}
