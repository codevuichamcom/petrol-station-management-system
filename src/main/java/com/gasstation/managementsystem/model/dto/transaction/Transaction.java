package com.gasstation.managementsystem.model.dto.transaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    private int id;
    private long time;
    private double volume;
    private double unitPrice;

}
