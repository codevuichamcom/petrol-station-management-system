package com.gasstation.managementsystem.model.dto.user;

import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserDTOUpdate {
    private String identityCardNumber;
    private String name;
    private Boolean gender;
    private Date dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private String note;
    private Double cashLimit;
    private Date limitSetDate;
    private Integer userTypeId;
    private AccountDTOUpdate account;


}
