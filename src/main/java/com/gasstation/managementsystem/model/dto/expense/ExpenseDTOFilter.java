package com.gasstation.managementsystem.model.dto.expense;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExpenseDTOFilter {
    private Integer pageIndex;
    private Integer pageSize;
    private String reason;
    private Double amount;
    private Long createdDate;
    private Integer[] stationIds;
    private String creatorName;

}
