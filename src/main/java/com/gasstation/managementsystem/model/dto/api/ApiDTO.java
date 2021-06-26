package com.gasstation.managementsystem.model.dto.api;

import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ApiDTO {
    private int id;

    private String name;

    private String method;

    private String path;

    private List<UserTypeDTO> accessibleUserTypes;
}
