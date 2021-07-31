package com.gasstation.managementsystem.model.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class EmployeeDTOCreate {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Name is mandatory")
    private String address;

    @Schema(description = "Include 10 or 11 digit", example = "0123456789")
    @NotBlank(message = "Phone is mandatory")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Phone just include 10 or 11 digit")
    private String phone;

    @NotNull(message = "Name is mandatory")
    private Boolean gender;

    @NotNull(message = "Date of birth is mandatory")
    private Long dateOfBirth;

    @Schema(example = "123456789", description = "Identity card number is composed of 9, 10, 12 or 13 digits")
    @NotBlank(message = "Identity card number is mandatory")
    @Pattern(regexp = "^[0-9]{9}|[0-9]{10}|[0-9]{12}|[0-9]{13}$", message = "Identity card number is composed of 9, 10, 12 or 13 digits")
    private String identityCardNumber;

    @NotNull(message = "Name is mandatory")
    private Integer stationId;

}
