package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.UserType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserTypeDTO {
    private int id;
    private String type;
    private List<Account> accounts;

    public UserTypeDTO(UserType userType) {
        this.id = userType.getId();
        this.type = userType.getType();
        this.accounts = userType.getAccounts();
    }
}
