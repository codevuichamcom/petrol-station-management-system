package com.gasstation.managementsystem.model.dto.card;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CardDTOFilter {
    public static final String STATUS_ACTIVATED = "ACTIVATED";
    public static final String STATUS_DEACTIVATED = "DEACTIVATED";
    private Integer pageIndex;
    private Integer pageSize;
    private Double accountsPayableFrom;
    private Double accountsPayableTo;
    private Double availableBalanceFrom;
    private Double availableBalanceTo;
    private String[] statuses;
    private String customerName;
    private Integer customerId;
}
