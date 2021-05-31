package com.gasstation.managementsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AccountDTO {
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private UserDTO userInfo;

    public AccountDTO(Account account) {
        if (account != null) {
            if (account.getId() != null) {
                this.id = account.getId();
            }
            this.username = account.getUsername();
            this.password = account.getPassword();
        }
    }
}
