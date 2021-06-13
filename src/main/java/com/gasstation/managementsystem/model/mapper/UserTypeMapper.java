package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;

public class UserTypeMapper {
    public static UserTypeDTO toUserTypeDTO(UserType userType) {
        if (userType == null) return null;
        return UserTypeDTO.builder()
                .id(userType.getId())
                .type(userType.getType()).build();
    }
}
