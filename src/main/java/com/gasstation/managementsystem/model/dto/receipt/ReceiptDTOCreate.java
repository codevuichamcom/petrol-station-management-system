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
    @NotNull(message = "Date is mandatory")
    private Long date;

    @NotBlank(message = "Reason is mandatory")
    private String reason;

    @NotNull(message = "Amount is mandatory")
    private Double amount;

    @NotNull(message = "Discount is mandatory")
    private Double discount;

    @NotBlank(message = "Note is mandatory")
    private String note;

    @NotNull(message = "Creator Id is mandatory")
    private Integer creatorId;

    @NotNull(message = "Card Id is mandatory")
    private UUID cardId;
}
