package com.gasstation.managementsystem.model.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AccountDTOCreate {

    @Schema(description = "Username has length greater than 3", example = "user")
    @NotBlank(message = "Username is mandatory")
    @Length(min = 4, message = "Username has length greater than 3")
    private String username;

    @Schema(description = "Password must contain at least 8 characters and include both letters and numbers", example = "1234567a")
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{8,}$",message = "Password must contain at least 8 characters and include both letters and numbers")
    private String password;
}
