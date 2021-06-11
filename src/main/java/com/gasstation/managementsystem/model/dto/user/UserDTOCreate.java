package com.gasstation.managementsystem.model.dto.user;

import com.gasstation.managementsystem.model.dto.account.AccountDTOCreate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserDTOCreate {

    @Schema(example = "123456789", description = "Identity card number is composed of 9 or 12 digits")
    @NotBlank(message = "Identity card number is mandatory")
    @Pattern(regexp = "^[0-9]{9}|[0-9]{12}$", message = "Identity card number is composed of 9 or 12 digits")
    private String identityCardNumber;//số chứng minh nhân dân

    @Schema(example = "Lê Hồng Quân")
    @NotBlank(message = "Name is mandatory")
    private String name;

    private boolean gender = false;

    @Schema(description = "Must be in past")
    @NotBlank(message = "Date of birth is mandatory")
    @Past(message = "Must be in past")
    private Date dateOfBirth;

    @Schema(example = "Hà Nội")
    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Phone is mandatory")
    @Pattern(regexp = "^[0-9]+$", message = "Phone just include digit")
    private String phone;

    @Email(message = "Field must be email")
    @Schema(example = "quan@gmail.com")
    private String email;

    private String note;

    private double cashLimit = 0;

    private Date limitSetDate = new Date();
    @Schema(description = "User type is positive integer and required")
    @Positive(message = "User type id is positive integer")
    private int userTypeId;

    private AccountDTOCreate account;


}
