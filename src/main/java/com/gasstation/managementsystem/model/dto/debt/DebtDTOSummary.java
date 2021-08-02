package com.gasstation.managementsystem.model.dto.debt;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DebtDTOSummary {
    private UUID cardId;
    private Integer stationId;
    private String stationName;
    private String stationAddress;
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private Double totalMoney;
}
