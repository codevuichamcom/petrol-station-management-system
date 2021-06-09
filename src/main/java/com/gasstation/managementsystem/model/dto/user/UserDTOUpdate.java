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
    private boolean gender;
    private Date dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private String note;
    private double cashLimit;
    private Date limitSetDate;
    private int userTypeId;
    private AccountDTOUpdate account;


}
