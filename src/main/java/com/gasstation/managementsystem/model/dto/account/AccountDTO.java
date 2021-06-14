package com.gasstation.managementsystem.model.dto.account;

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
    private boolean isActive;

}
