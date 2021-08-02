package com.gasstation.managementsystem.model.dto.card;

import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CardDTO {
    private UUID id;
    private String driverPhone;
    private String driverName;
    private String licensePalates;
    private Double initialDebt;
    private Double availableBalance;
    private Double accountsPayable;
    private Double debtLimit;
    private Long limitSetDate;
    private Long issuedDate;
    private Long activeDate;
    private UserDTO activateUser;
    private UserDTO customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDTO cardDTO = (CardDTO) o;
        return Objects.equals(id, cardDTO.id) && Objects.equals(driverPhone, cardDTO.driverPhone) && Objects.equals(driverName, cardDTO.driverName) && Objects.equals(licensePalates, cardDTO.licensePalates) && Objects.equals(initialDebt, cardDTO.initialDebt) && Objects.equals(availableBalance, cardDTO.availableBalance) && Objects.equals(accountsPayable, cardDTO.accountsPayable) && Objects.equals(debtLimit, cardDTO.debtLimit) && Objects.equals(limitSetDate, cardDTO.limitSetDate) && Objects.equals(issuedDate, cardDTO.issuedDate) && Objects.equals(activeDate, cardDTO.activeDate) && Objects.equals(activateUser, cardDTO.activateUser) && Objects.equals(customer, cardDTO.customer);
    }
}
