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
    private Date date;
    private String note;
    @NotBlank(message = "Station id is mandatory")
    private Integer stationId;
    @NotBlank(message = "Fuel import id is mandatory")
    private Integer fuelImportId;
}
