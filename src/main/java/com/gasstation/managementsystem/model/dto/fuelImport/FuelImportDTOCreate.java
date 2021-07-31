package com.gasstation.managementsystem.model.dto.fuelImport;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelImportDTOCreate {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Date is mandatory")
    private Long createdDate;

    @NotNull(message = "Volume is mandatory")
    private Double volume;

    @NotNull(message = "Unit price is mandatory")
    private Double unitPrice;

    @NotNull(message = "Paid is mandatory")
    private Double amountPaid;

    @NotNull(message = "VAT percent is mandatory")
    private Double vatPercent;

    private String note;

    @NotNull(message = "Tank id is mandatory")
    private Integer tankId;

    @NotNull(message = "Supplier id is mandatory")
    private Integer supplierId;

    @NotNull(message = "Fuel id is mandatory")
    private Integer fuelId;
}