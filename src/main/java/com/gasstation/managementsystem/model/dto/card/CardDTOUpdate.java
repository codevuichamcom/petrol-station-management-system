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
    private Double availableBalance;
    private Double debtLimit;
    private Boolean active;
}
