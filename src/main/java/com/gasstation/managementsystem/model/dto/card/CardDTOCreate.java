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
    @NotBlank(message = "Driver phone is mandatory")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Phone just include 10 or 11 digit")
    private String driverPhone;
    @NotBlank(message = "Driver name is mandatory")
    private String driverName;
    @NotBlank(message = "License Palates is mandatory")
    private String licensePlate;
    @NotNull(message = "Available Balance is mandatory")
    private Double availableBalance;
    @NotNull(message = "Debt Limit is mandatory")
    private Double debtLimit;
    private Long createdDate;
    private Integer activateUserId;
    private Integer customerId;
    private Boolean active;

}
