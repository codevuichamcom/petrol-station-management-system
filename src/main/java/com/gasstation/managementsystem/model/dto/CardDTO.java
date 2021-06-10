package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.User;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CardDTO {
    private int id;
    private String driverPhone;
    private String driverName;
    private String licensePalates;
    private double initialDebt;
    private double availableBalance;
    private double outstandingBalance;
    private Date limitSetDate;
    private Date issuedDate;
    private Date activeDate;
//    private User userCard;
    private User userActive;

    public CardDTO(Card card) {
//        this.id = card.getId();
//        this.driverPhone = card.getDriverPhone();
//        this.driverName = card.getDriverPhone();
//        this.licensePalates = card.getLicensePalates();
//        this.availableBalance = card.getAvailableBalance();
//        this.outstandingBalance = card.getOutstandingBalance();
//        this.limitSetDate = card.getLimitSetDate();
//        this.issuedDate = card.getIssuedDate();
//        this.activeDate = card.getActiveDate();
////        this.userCard = card.getUserCard();
//        this.userActive = card.getUserActive();
    }
}
