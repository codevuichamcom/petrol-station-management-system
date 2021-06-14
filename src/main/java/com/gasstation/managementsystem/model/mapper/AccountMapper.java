package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.account.AccountDTO;
import com.gasstation.managementsystem.model.dto.account.AccountDTOCreate;
import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class AccountMapper {
    public static Account toAccount(AccountDTOCreate accountDTOCreate) {
        if (accountDTOCreate == null) return null;
        return Account.builder()
                .username(accountDTOCreate.getUsername())
                .password(accountDTOCreate.getPassword())
                .isActive(true).build();
    }


    public static void copyNonNullToAccount(Account account, AccountDTOUpdate accountDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(account, accountDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static AccountDTO toAccountDTO(Account account) {
        if (account == null) return null;
        User userInfo = account.getUserInfo();
        String name = userInfo != null ? userInfo.getName() : null;

        String type = null;
        if (userInfo != null) {
            UserType userType = userInfo.getUserType();
            type = (userType != null && userType.getType() != null)
                    ? userType.getType()
                    : null;
        }
        return AccountDTO.builder()
                .id(account.getId())
                .username(account.getUsername())
                .isActive(account.isActive())
                .name(name)
                .userType(type).build();
    }

}
