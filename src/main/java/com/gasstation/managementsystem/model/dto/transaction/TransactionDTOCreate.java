package com.gasstation.managementsystem.model.dto.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "unix time")
    private Date time;
    @NotNull(message = "number of litter is mandatory")
    private Double volume;
    @NotNull(message = "pricePerLitter is mandatory")
    private Double unitPrice;
    @NotNull(message = "pricePerLitter is mandatory")
    private String uuid;
    private Integer cardId;
    @NotNull(message = "Pump ìd is mandatory")
    private Integer pumpId;

}
