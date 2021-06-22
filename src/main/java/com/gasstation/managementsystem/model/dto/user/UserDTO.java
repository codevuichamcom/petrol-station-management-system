package com.gasstation.managementsystem.model.dto.user;

import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserDTO {
    private int id;
    private String username;
    private String identityCardNumber;//số chứng minh nhân dân
    private String name;
    private Boolean gender;
    private String dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private String note;
    private Boolean active;
    private UserTypeDTO userType;

}
