package com.gasstation.managementsystem.model.dto.card;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CardDTOUpdate {
    private String driverPhone;
    private String driverName;
    private String licensePalates;
    private Double initialDebt;
    private Double availableBalance;
    private Double outstandingBalance;
    private Double debtLimit;
    private Date limitSetDate;
    private Date issuedDate;
    private Date activeDate;
    private Integer activateUserId;
    private Integer customerId;

}
