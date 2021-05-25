package com.gasstation.managementsystem.entity;

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
}
