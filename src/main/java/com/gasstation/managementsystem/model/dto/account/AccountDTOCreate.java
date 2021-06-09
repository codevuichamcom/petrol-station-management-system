package com.gasstation.managementsystem.model.dto.account;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AccountDTOCreate {
    private String username;
    private String password;
}
