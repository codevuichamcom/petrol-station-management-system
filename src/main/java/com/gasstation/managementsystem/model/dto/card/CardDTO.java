package com.gasstation.managementsystem.model.dto.card;

import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CardDTO {
    private int id;
    private String driverPhone;
    private String driverName;
    private String licensePalates;
    private Double initialDebt;
    private Double availableBalance;
    private Double outstandingBalance;
    private Double debtLimit;
    private String limitSetDate;
    private String issuedDate;
    private String activeDate;
    private UserDTO activateUser;
    private UserDTO customer;

}
