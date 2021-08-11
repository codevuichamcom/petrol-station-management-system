package com.gasstation.managementsystem.model.dto.fuelImport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelImportDTOUpdate {
    private String name;
    private Long importDate;
    private Double accountsPayable;
    @Schema(description = "Reason when pay expense")
    private String reason;
    private String note;
}