package com.gasstation.managementsystem.model.dto.expense;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    @NotNull(message = "Date is mandatory")
    private Long createdDate;
    private String note;
    @NotNull(message = "Station id is mandatory")
    private Integer stationId;
    @NotNull(message = "Fuel import id is mandatory")
    private Integer fuelImportId;
}
