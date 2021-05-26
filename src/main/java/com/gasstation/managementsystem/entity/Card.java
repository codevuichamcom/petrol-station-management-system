package com.gasstation.managementsystem.entity;

import com.gasstation.managementsystem.model.dto.CardDTO;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "card_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userCard;

    @ManyToOne
    @JoinColumn(name = "activate_user_id")
    private User userActive;

    public Card(CardDTO cardDTO) {
        this.id = cardDTO.getId();
        this.driverPhone = cardDTO.getDriverPhone();
        this.driverName = cardDTO.getDriverPhone();
        this.licensePalates = cardDTO.getLicensePalates();
        this.availableBalance = cardDTO.getAvailableBalance();
        this.outstandingBalance = cardDTO.getOutstandingBalance();
        this.limitSetDate = cardDTO.getLimitSetDate();
        this.issuedDate = cardDTO.getIssuedDate();
        this.activeDate = cardDTO.getActiveDate();
        this.userCard = cardDTO.getUserCard();
        this.userActive = cardDTO.getUserActive();
    }
}
