package com.gasstation.managementsystem.model.dto.expense;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExpenseDTOCreate {
    @NotBlank(message = "Reason is mandatory")
    private String reason;
    @NotNull(message = "Amount is mandatory")
    private Double amount;
    @NotNull(message = "Station id is mandatory")
    private Integer stationId;
    private Integer fuelImportId;

}
