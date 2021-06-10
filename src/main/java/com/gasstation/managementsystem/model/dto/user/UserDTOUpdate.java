package com.gasstation.managementsystem.model.dto.user;

import com.gasstation.managementsystem.model.dto.account.AccountDTOUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserDTOUpdate {
    @Schema(example = "123456789", description = "Identity card number is composed of 9 or 12 digits")
    @Pattern(regexp = "^[0-9]{9}|[0-9]{12}$", message = "Identity card number is composed of 9 or 12 digits")
    private String identityCardNumber;

    @Schema(example = "Lê Hồng Quân")
    private String name;

    private Boolean gender;

    @Schema(description = "Must be in past")
    @Past(message = "Must be in past")
    private Date dateOfBirth;

    @Schema(description = "Hà Nội")
    private String address;

    @Pattern(regexp = "^[0-9]+$", message = "Phone just include digit")
    private String phone;

    @Schema(example = "quan@gmail.com")
    @Email(message = "Field must be email")
    private String email;

    private String note;

    private Double cashLimit;

    private Date limitSetDate;

    @Schema(description = "User type is positive integer and required")
    @Positive(message = "User type id is positive integer")
    private Integer userTypeId;

}
