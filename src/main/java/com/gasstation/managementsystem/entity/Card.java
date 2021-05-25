package com.gasstation.managementsystem.entity;

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
}
