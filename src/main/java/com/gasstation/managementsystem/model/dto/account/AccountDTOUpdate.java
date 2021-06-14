package com.gasstation.managementsystem.model.dto.account;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AccountDTOUpdate {
    private String oldPassword;

    @NotBlank(message = "new password is mandatory")
    private String newPassword;
}
