package com.gasstation.managementsystem.model.dto.user;

import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.model.dto.account.AccountDTO;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserDTO {
    private int id;
    private String identityCardNumber;//số chứng minh nhân dân
    private String name;
    private Boolean gender;
    private Date dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private String note;
    private Double cashLimit;
    private Date limitSetDate;
    private UserTypeDTO userType;
    private AccountDTO account;

}
