package com.gasstation.managementsystem.model.dto.expense;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExpenseDTOUpdate {
    private String reason;
    private Double amount;
    private Long createdDate;
    private String note;
    private Integer stationId;
    private Integer fuelImportId;
}
