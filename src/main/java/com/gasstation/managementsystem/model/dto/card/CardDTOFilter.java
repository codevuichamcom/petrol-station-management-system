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
    private Double accountsPayable;
    private Double availableBalance;
    private String[] statuses;
    private Long createdDate;
    private String customerName;
    private String customerPhone;
    private String licensePlate;
}
