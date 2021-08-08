package com.gasstation.managementsystem.model.dto.receipt;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ReceiptDTOFilter {
    private Integer pageIndex;
    private Integer pageSize;
    private Long createdDate;
    private Double amount;
    private String reason;
    private String cardId;
    private String customerName;
    private String customerPhone;
    private String creatorName;
}
