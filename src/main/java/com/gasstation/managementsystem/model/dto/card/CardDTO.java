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
public class CardDTO {
    private UUID id;
    private String driverPhone;
    private String driverName;
    private String licensePlate;
    private Double initialDebt;
    private Double availableBalance;
    private Double accountsPayable;
    private Double debtLimit;
    private Long limitSetDate;
    private Long createdDate;
    private Boolean active;
    private UserDTO creator;
    private UserDTO customer;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDTO cardDTO = (CardDTO) o;
        return Objects.equals(id, cardDTO.id) && Objects.equals(driverPhone, cardDTO.driverPhone) && Objects.equals(driverName, cardDTO.driverName) && Objects.equals(licensePlate, cardDTO.licensePlate) && Objects.equals(initialDebt, cardDTO.initialDebt) && Objects.equals(availableBalance, cardDTO.availableBalance) && Objects.equals(accountsPayable, cardDTO.accountsPayable) && Objects.equals(debtLimit, cardDTO.debtLimit) && Objects.equals(limitSetDate, cardDTO.limitSetDate) && Objects.equals(createdDate, cardDTO.createdDate) && Objects.equals(customer, cardDTO.customer);
    }
}
