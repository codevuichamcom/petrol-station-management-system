package com.gasstation.managementsystem.model.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AccountDTOCreate {

    @Schema(description = "Username has length greater than 3", example = "user")
    @NotBlank(message = "Username is mandatory")
    @Length(min = 3, message = "Username has length greater than 3")
    private String username;

    @Schema(description = "Username has length greater than 5", example = "123456")
    @NotBlank(message = "Password is mandatory")
    @Length(min = 6, message = "Password has length greater than 5")
    private String password;
}
