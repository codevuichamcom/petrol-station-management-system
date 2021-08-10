package com.gasstation.managementsystem.model.dto.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionDTOCreate {
    @NotNull(message = "time is mandatory")
    @Schema(description = "unix time")
    private long time;
    @NotNull(message = "number of litter is mandatory")
    private Double volume;
    @NotNull(message = "pricePerLitter is mandatory")
    private Double unitPrice;
    @NotNull(message = "totalAmount is mandatory")
    private Double totalAmount;
    @NotNull(message = "uuid is mandatory")
    private String uuid;
    private UUID cardId;
    @NotNull(message = "Pump ìd is mandatory")
    private Integer pumpId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTOCreate that = (TransactionDTOCreate) o;
        return Objects.equals(time, that.time) && Objects.equals(volume, that.volume) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(cardId, that.cardId);
    }
}
