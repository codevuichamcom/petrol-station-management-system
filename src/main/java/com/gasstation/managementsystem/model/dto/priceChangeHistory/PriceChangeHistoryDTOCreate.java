package com.gasstation.managementsystem.model.dto.priceChangeHistory;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PriceChangeHistoryDTOCreate {
    @NotNull
    private Date date;
    @NotNull
    private Double newPrice;
    @NotNull
    private Integer editorId;
    @NotNull
    private Integer stationId;
    @NotNull
    private Integer tankId;
}
