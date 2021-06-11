package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.model.dto.account.AccountDTO;
import com.gasstation.managementsystem.model.dto.account.AccountDTOCreate;
import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdate;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class AccountMapper {
    public static Account toAccount(AccountDTOCreate accountDTOCreate){
            return Account.builder()
                    .username(accountDTOCreate.getUsername())
                    .password(accountDTOCreate.getPassword()).build();
    }


    public static void copyNonNullToAccount (Account account, AccountDTOUpdate accountDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(account, accountDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static AccountDTO toAccountDTO(Account account){
        if(account==null) return null;
            return AccountDTO.builder()
                    .id(account.getId())
                    .username(account.getUsername())
                    .name(account.getUserInfo().getName())
                    .userType(account.getUserInfo().getUserType().getType()).build();
    }

}
