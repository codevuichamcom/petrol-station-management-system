package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PriceChangeHistoryDTO {
    private int id;

    private Date date;
    private double price;

    public PriceChangeHistoryDTO(PriceChangeHistory priceChangeHistory) {
//        this.id = priceChangeHistory.getId();
//        this.date = priceChangeHistory.getDate();
//        this.price = priceChangeHistory.getPrice();
    }
}
