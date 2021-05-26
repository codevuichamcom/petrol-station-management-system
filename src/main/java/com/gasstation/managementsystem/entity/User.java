package com.gasstation.managementsystem.entity;

import com.gasstation.managementsystem.model.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "user_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String note;
    private double cashLimit;
    private Date limitSetDate;

    @OneToOne
    private Account account;


    @OneToMany(mappedBy = "userCard", cascade = CascadeType.ALL)
    private List<Card> cards;
    @OneToMany(mappedBy = "userActive", cascade = CascadeType.ALL)
    private List<Card> cardsActive;

    @OneToMany(mappedBy = "user")
    private List<Station> station;

    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.fullName = userDTO.getFullName();
        this.address = userDTO.getAddress();
        this.phone = userDTO.getPhone();
        this.email = userDTO.getEmail();
        this.note = userDTO.getNote();
        this.cashLimit = userDTO.getCashLimit();
        this.limitSetDate = userDTO.getLimitSetDate();
        this.account = userDTO.getAccount();
        this.cards = userDTO.getCards();
        this.cardsActive = userDTO.getCardsActive();
        this.station = userDTO.getStation();
    }
}
