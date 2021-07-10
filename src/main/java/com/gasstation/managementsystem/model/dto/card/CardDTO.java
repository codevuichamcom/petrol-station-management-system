package com.gasstation.managementsystem.model.dto.card;

import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDTO cardDTO = (CardDTO) o;
        return id == cardDTO.id && Objects.equals(driverPhone, cardDTO.driverPhone) && Objects.equals(driverName, cardDTO.driverName) && Objects.equals(licensePalates, cardDTO.licensePalates) && Objects.equals(initialDebt, cardDTO.initialDebt) && Objects.equals(availableBalance, cardDTO.availableBalance) && Objects.equals(outstandingBalance, cardDTO.outstandingBalance) && Objects.equals(debtLimit, cardDTO.debtLimit) && Objects.equals(limitSetDate, cardDTO.limitSetDate) && Objects.equals(issuedDate, cardDTO.issuedDate) && Objects.equals(activeDate, cardDTO.activeDate) && Objects.equals(activateUser, cardDTO.activateUser) && Objects.equals(customer, cardDTO.customer);
    }

}
