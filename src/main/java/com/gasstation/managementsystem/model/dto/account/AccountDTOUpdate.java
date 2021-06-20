package com.gasstation.managementsystem.model.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AccountDTOUpdate {
    private String oldPassword;

    @Schema(description = "Password must contain at least 8 characters and include both letters and numbers", example = "1234567a")
    @NotBlank(message = "new password is mandatory")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{8,}$",message = "Password must contain at least 8 characters and include both letters and numbers")
    private String newPassword;
}
