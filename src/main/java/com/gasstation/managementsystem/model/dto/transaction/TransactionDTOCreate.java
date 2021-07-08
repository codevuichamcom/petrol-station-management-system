package com.gasstation.managementsystem.model.dto.transaction;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionDTOCreate {
    @NotNull(message = "time is mandatory")
    private Date time;
    @NotNull(message = "number of litter is mandatory")
    private Double volume;
    @NotNull(message = "pricePerLitter is mandatory")
    private Double unitPrice;
    @NotNull(message = "Card ìd is mandatory")
    private Integer cardId;

}
