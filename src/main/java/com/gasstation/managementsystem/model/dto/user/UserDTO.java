package com.gasstation.managementsystem.model.dto.user;

import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import lombok.*;

import java.util.Objects;


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
    private Long dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private String note;
    private Boolean active;
    private UserTypeDTO userType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && Objects.equals(username, userDTO.username) && Objects.equals(identityCardNumber, userDTO.identityCardNumber) && Objects.equals(name, userDTO.name) && Objects.equals(gender, userDTO.gender) && Objects.equals(dateOfBirth, userDTO.dateOfBirth) && Objects.equals(address, userDTO.address) && Objects.equals(phone, userDTO.phone) && Objects.equals(email, userDTO.email) && Objects.equals(note, userDTO.note) && Objects.equals(active, userDTO.active) && Objects.equals(userType, userDTO.userType);
    }

}
