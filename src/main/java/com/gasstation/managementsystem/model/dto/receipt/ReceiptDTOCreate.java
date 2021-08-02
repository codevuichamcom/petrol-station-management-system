package com.gasstation.managementsystem.model.dto.receipt;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReceiptDTOCreate {
    @NotBlank(message = "Reason is mandatory")
    private String reason;

    @NotNull(message = "Amount is mandatory")
    private Double amount;

    @NotNull(message = "Discount is mandatory")
    private Double discount;

    @NotNull(message = "Card Id is mandatory")
    private UUID cardId;

    @NotNull(message = "Debt Id is mandatory")
    private Integer debtId;
}
