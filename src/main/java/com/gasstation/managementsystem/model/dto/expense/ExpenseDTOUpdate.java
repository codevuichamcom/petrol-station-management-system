package com.gasstation.managementsystem.model.dto.expense;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExpenseDTOUpdate {
    private String reason;
    private Double amount;
    private Date date;
    private String note;
    private Integer stationId;
    private Integer fuelImportId;
}
