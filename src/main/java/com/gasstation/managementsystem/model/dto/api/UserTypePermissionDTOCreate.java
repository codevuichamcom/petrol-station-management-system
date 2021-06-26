package com.gasstation.managementsystem.model.dto.api;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserTypePermissionDTOCreate {
    @NotNull(message = "UserTypeId is mandatory")
    private Integer userTypeId;
}
