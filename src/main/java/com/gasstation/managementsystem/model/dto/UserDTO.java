package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.User;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserDTO {
    private Integer id;
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

    public UserDTO(User user) {
        if (user != null) {
            if (user.getId() != null) {
                this.id = user.getId();
            }
            this.identityCardNumber = user.getIdentityCardNumber();
            this.name = user.getName();
            this.gender = user.getGender();
            this.dateOfBirth = user.getDateOfBirth();
            this.address = user.getAddress();
            this.phone = user.getPhone();
            this.email = user.getEmail();
            this.note = user.getNote();
            this.cashLimit = user.getCashLimit();
            this.limitSetDate = user.getLimitSetDate();
            this.userType = new UserTypeDTO(user.getUserType());
            if (user.getAccount() != null) {
                this.account = new AccountDTO(user.getAccount());
            }
        }
    }
}
