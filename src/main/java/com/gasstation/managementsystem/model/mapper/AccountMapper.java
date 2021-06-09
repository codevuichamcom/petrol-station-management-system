package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.model.dto.account.AccountDTO;
import com.gasstation.managementsystem.model.dto.account.AccountDTOCreate;
import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;

public class AccountMapper {
    public static Account toAccount(AccountDTOCreate accountDTOCreate){
            return Account.builder()
                    .username(accountDTOCreate.getUsername())
                    .password(accountDTOCreate.getPassword()).build();
    }

    public static Account toAccount(AccountDTOUpdate accountDTOUpdate){
        return Account.builder()
                .username(accountDTOUpdate.getUsername())
                .password(accountDTOUpdate.getPassword()).build();
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
