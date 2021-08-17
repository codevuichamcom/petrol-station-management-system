package com.gasstation.managementsystem.model.dto.transaction;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionDTOFilter {
    private Integer pageIndex;
    private Integer pageSize;
    private Long timeFrom;
    private Long timeTo;
    private Double unitPriceFrom;
    private Double unitPriceTo;
    private Double volumeFrom;
    private Double volumeTo;
    private Double amountFrom;
    private Double amountTo;
    private String pumpName;
    private String shiftName;
    private Integer[] stationIds;
    private String stationName;
    private Integer customerId;
}
