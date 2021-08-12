package com.gasstation.managementsystem.model.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class EmployeeDTOUpdate {
    private String name;
    private String address;
    @Schema(description = "Include 10 or 11 digit", example = "0123456789")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Phone just include 10 or 11 digit")
    private String phone;
    private Boolean gender;
    private Long dateOfBirth;
    @Schema(example = "123456789", description = "Identity card number is composed of 9, 10, 12 or 13 digits")
    @Pattern(regexp = "^[0-9]{9}|[0-9]{10}|[0-9]{12}|[0-9]{13}$", message = "Identity card number is composed of 9, 10, 12 or 13 digits")
    private String identityCardNumber;
}
