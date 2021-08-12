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
    private Integer[] pumpIds;
    private Integer[] shiftIds;
    private Integer[] stationIds;
    private Long timeFrom;
    private Long timeTo;
    private Double unitPriceFrom;
    private Double unitPriceTo;
    private Double volumeFrom;
    private Double volumeTo;
}
