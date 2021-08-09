package com.gasstation.managementsystem.model.dto.api;

import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import lombok.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiDTO {
    private int id;

    private String method;

    private String path;
    private List<UserTypeDTO> accessibleUserTypes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiDTO apiDTO = (ApiDTO) o;
        return id == apiDTO.id && method.equals(apiDTO.method) && path.equals(apiDTO.path) && Objects.equals(accessibleUserTypes, apiDTO.accessibleUserTypes);
    }
}
