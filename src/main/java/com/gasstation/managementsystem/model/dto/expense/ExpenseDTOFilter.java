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
    private Double amountFrom;
    private Double amountTo;
    private Long createdDateFrom;
    private Long createdDateTo;
    private String stationName;
    private String creatorName;
    private String reason;
    private Integer ownerId;

}
