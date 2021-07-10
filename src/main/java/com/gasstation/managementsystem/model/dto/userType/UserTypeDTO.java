package com.gasstation.managementsystem.model.dto.userType;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserTypeDTO {
    private int id;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTypeDTO that = (UserTypeDTO) o;
        return id == that.id && Objects.equals(type, that.type);
    }
}
