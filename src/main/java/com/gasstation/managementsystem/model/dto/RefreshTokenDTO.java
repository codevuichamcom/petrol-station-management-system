package com.gasstation.managementsystem.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class RefreshTokenDTO {
    @NotNull
    private String refreshToken;
}
