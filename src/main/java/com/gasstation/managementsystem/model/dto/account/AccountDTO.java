package com.gasstation.managementsystem.model.dto.account;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AccountDTO {
    private int id;
    private String username;
    private String name;
    private String userType;

}
