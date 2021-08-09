package com.gasstation.managementsystem.model.dto.debt;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DebtDTOPay {
    @NotNull(message = "debtId is mandatory")
    private Integer debtId;
    @NotNull(message = "transactionId is mandatory")
    private Integer transactionId;
    @NotNull(message = "cardId is mandatory")
    private UUID cardId;
    @NotNull(message = "Reason is mandatory")
    private String reason;

}
