package com.gasstation.managementsystem.model.dto.card;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CardDTOUpdate {
    private String driverPhone;
    private String driverName;
    private String licensePlate;
    private Double initialDebt;
    private Double payInAmount;
    private Double debtLimit;
    private Integer customerId;
    private Boolean active;
}
