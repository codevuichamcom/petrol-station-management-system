package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.account.AccountDTO;
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


    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;
        Account account = user.getAccount();
        AccountDTO accountDTO = AccountMapper.toAccountDTO(account);
        if (accountDTO != null) {
            accountDTO.setUsername(null);
            accountDTO.setUserType(null);
        }
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
                .userType(UserTypeMapper.toUserTypeDTO(user.getUserType()))
                .account(accountDTO).build();
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
