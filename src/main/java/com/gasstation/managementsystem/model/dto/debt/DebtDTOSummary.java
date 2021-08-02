package com.gasstation.managementsystem.model.dto.debt;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DebtDTOSummary {
    private String cardId;
    private Integer stationId;
    private String stationName;
    private String stationAddress;
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private Double totalMoney;
}
