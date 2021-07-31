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
    private String licensePalates;
    @NotNull(message = "Initial Debt is mandatory")
    private Double initialDebt;
    @NotNull(message = "Available Balance is mandatory")
    private Double availableBalance;
    @NotNull(message = "Outstanding Balance is mandatory")
    private Double outstandingBalance;
    @NotNull(message = "Debt Limit is mandatory")
    private Double debtLimit;
    @NotNull(message = "limit Set Date is mandatory")
    private Long limitSetDate;
    @NotNull(message = "Issued Date is mandatory")
    private Long issuedDate;
    @NotNull(message = "Active Date is mandatory")
    private Long activeDate;
    @NotNull(message = "Activate User Id is mandatory")
    private Integer activateUserId;
    @NotNull(message = "Customer id is mandatory")
    private Integer customerId;

}
