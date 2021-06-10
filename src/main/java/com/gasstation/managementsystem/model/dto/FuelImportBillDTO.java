package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.FuelImportBill;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuelImportBillDTO {
    private int id;

    private String name;
    private Date date;
    private double numberOfLiters;
    private double pricePerLiter;
    private String note;
    private double vatPercent;

    public FuelImportBillDTO(FuelImportBill fuelImportBill) {
//        this.id = fuelImportBill.getId();
//        this.name = fuelImportBill.getName();
//        this.date = fuelImportBill.getDate();
//        this.numberOfLiters = fuelImportBill.getNumberOfLiters();
//        this.pricePerLiter = fuelImportBill.getPricePerLiter();
//        this.note = fuelImportBill.getNote();
//        this.vatPercent = fuelImportBill.getVatPercent();
    }
}
