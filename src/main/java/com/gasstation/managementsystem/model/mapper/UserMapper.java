package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.UserTypeDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class UserMapper {
    public static User toUser(UserDTOCreate userDTOCreate) {
        return User.builder()
                .identityCardNumber(userDTOCreate.getIdentityCardNumber())
                .name(userDTOCreate.getName())
                .gender(userDTOCreate.isGender())
                .dateOfBirth(userDTOCreate.getDateOfBirth())
                .address(userDTOCreate.getAddress())
                .phone(userDTOCreate.getPhone())
                .email(userDTOCreate.getEmail())
                .note(userDTOCreate.getNote())
                .cashLimit(userDTOCreate.getCashLimit())
                .limitSetDate(userDTOCreate.getLimitSetDate())
                .userType(UserType.builder().id(userDTOCreate.getUserTypeId()).build()).build();
    }

    public static User toUser(UserDTOUpdate userDTOUpdate) {
        return User.builder()
                .identityCardNumber(userDTOUpdate.getIdentityCardNumber())
                .name(userDTOUpdate.getName())
                .gender(userDTOUpdate.getGender())
                .dateOfBirth(userDTOUpdate.getDateOfBirth())
                .address(userDTOUpdate.getAddress())
                .phone(userDTOUpdate.getPhone())
                .email(userDTOUpdate.getEmail())
                .note(userDTOUpdate.getNote())
                .cashLimit(userDTOUpdate.getCashLimit())
                .limitSetDate(userDTOUpdate.getLimitSetDate())
                .userType(UserType.builder().id(userDTOUpdate.getUserTypeId()).build()).build();
    }

    public static void copyToUser(User user, UserDTOUpdate userDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(user, userDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .identityCardNumber(user.getIdentityCardNumber())
                .name(user.getName())
                .gender(user.isGender())
                .dateOfBirth(user.getDateOfBirth())
                .address(user.getAddress())
                .phone(user.getPhone())
                .email(user.getEmail())
                .note(user.getNote())
                .cashLimit(user.getCashLimit())
                .limitSetDate(user.getLimitSetDate())
                .userType(
                        UserTypeDTO.builder()
                                .id(user.getUserType().getId())
                                .type(user.getUserType().getType()).build()
                )
                .account(AccountMapper.toAccountDTO(user.getAccount())).build();
    }
}
