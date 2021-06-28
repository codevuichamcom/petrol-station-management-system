package com.gasstation.managementsystem.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class RefreshTokenDTO {
    private String refreshToken;
}
