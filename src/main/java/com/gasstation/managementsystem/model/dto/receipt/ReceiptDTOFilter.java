package com.gasstation.managementsystem.model.dto.receipt;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReceiptDTOFilter {
    private Integer pageIndex;
    private Integer pageSize;
    private Long createdDateFrom;
    private Long createdDateTo;
    private Double amountFrom;
    private Double amountTo;
    private String customerName;
    private String creatorName;
    private String reason;
    private Integer ownerId;
}
