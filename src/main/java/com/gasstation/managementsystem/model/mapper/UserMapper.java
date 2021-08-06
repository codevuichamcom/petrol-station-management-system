package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .identityCardNumber(user.getIdentityCardNumber())
                .name(user.getName())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .address(user.getAddress())
                .phone(user.getPhone())
                .email(user.getEmail())
                .note(user.getNote())
                .active(user.getActive())
                .userType(UserTypeMapper.toUserTypeDTO(user.getUserType())).build();
    }

    public static User toUser(UserDTOCreate userDTOCreate) {
        if (userDTOCreate == null) return null;
        return User.builder()
                .identityCardNumber(userDTOCreate.getIdentityCardNumber())
                .username(userDTOCreate.getUsername())
                .password(userDTOCreate.getPassword())
                .name(userDTOCreate.getName())
                .gender(userDTOCreate.getGender())
                .dateOfBirth(userDTOCreate.getDateOfBirth())
                .address(userDTOCreate.getAddress())
                .phone(userDTOCreate.getPhone())
                .email(userDTOCreate.getEmail())
                .note(userDTOCreate.getNote())
                .active(true)
                .userType(UserType.builder().id(userDTOCreate.getUserTypeId()).build()).build();
    }

    public static void copyToUser(User user, UserDTOUpdate userDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(user, userDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
