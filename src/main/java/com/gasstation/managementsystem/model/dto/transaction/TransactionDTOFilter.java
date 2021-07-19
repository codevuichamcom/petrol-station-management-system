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
    private Long time;
    private Double unitPrice;
    private Double volume;
}
