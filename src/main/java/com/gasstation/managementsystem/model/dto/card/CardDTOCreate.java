package com.gasstation.managementsystem.model.dto.card;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CardDTOCreate {
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Phone just include 10 or 11 digit")
    private String driverPhone;
    private String driverName;
    private String licensePlate;
    private Double debtLimit;
    private Integer customerId;
    private Boolean active;

}
