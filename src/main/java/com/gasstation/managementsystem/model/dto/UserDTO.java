package com.gasstation.managementsystem.model.dto;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserDTO {
    private int id;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String note;
    private double cashLimit;
    private Date limitSetDate;
    private Account account;
    private List<Card> cards;
    private List<Card> cardsActive;
    private List<Station> station;

    public UserDTO(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.note = user.getNote();
        this.cashLimit = user.getCashLimit();
        this.limitSetDate = user.getLimitSetDate();
        this.account = user.getAccount();
        this.cards = user.getCards();
        this.cardsActive = user.getCardsActive();
        this.station = user.getStation();
    }
}
