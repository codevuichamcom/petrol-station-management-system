package com.gasstation.managementsystem.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @Schema(example = "123456789", description = "Identity card number is composed of 9, 10, 12 or 13 digits")
    @Pattern(regexp = "^[0-9]{9}|[0-9]{10}|[0-9]{12}|[0-9]{13}$", message = "Identity card number is composed of 9, 10, 12 or 13 digits")
    private String identityCardNumber;

    @Schema(example = "Lê Hồng Quân", description = "Length greater than 3")
    @Length(min = 3, message = "Length greater than 3")
    private String name;

    private Boolean gender;

    @Schema(description = "Must be in past")
    @Past(message = "Must be in past")
    private Date dateOfBirth;

    @Schema(example = "Hà Nội", description = "Length greater than 3")
    @Length(min = 3, message = "Address has length greater than 3")
    private String address;

    @Schema(description = "Include 10 or 11 digit", example = "0123456789")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Phone just include 10 or 11 digit")
    private String phone;

    @Schema(example = "quan@gmail.com")
    @Email(message = "Field must be email")
    private String email;

    private String note;

    private Double cashLimit;

    private Date limitSetDate;

    @Positive(message = "User type id is positive integer")
    private Integer userTypeId;

}
