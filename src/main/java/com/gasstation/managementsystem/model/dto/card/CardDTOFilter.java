package com.gasstation.managementsystem.model.dto.card;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CardDTOFilter {
    public static final String STATUS_ACTIVATED = "ACTIVATED";
    public static final String STATUS_DEACTIVATED = "DEACTIVATED";
    private Integer pageIndex;
    private Integer pageSize;
    private Double accountsPayable;
    private String[] statuses;
    private Long createdDate;
    private String activateUserName;
    private String customerName;
    private String licensePlate;
}
