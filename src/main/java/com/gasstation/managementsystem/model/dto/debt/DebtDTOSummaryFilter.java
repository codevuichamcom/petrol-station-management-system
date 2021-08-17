package com.gasstation.managementsystem.model.dto.debt;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DebtDTOSummaryFilter {
    private Integer pageIndex;
    private Integer pageSize;
    private String cardId;
    private Integer stationId;
    private String stationName;
    private String customerName;
    private Double totalAccountsPayableFrom;
    private Double totalAccountsPayableTo;
    private Integer[] stationIds;
    private Integer customerId;
}
