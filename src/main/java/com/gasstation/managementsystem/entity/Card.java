package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<PumpCode> pumpCodeList;//Danh sách mã bơm trả bằng thẻ này

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Payment> paymentList;//Danh sách thanh toán của Thẻ này

    @ManyToOne
    @JoinColumn(name = "activate_user_id")
    private User userActive; //Ai active thẻ này

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userInfo;//Người nào sử dụng thẻ này

//    public Card(CardDTO cardDTO) {
//        this.id = cardDTO.getId();
//        this.driverPhone = cardDTO.getDriverPhone();
//        this.driverName = cardDTO.getDriverPhone();
//        this.licensePalates = cardDTO.getLicensePalates();
//        this.availableBalance = cardDTO.getAvailableBalance();
//        this.outstandingBalance = cardDTO.getOutstandingBalance();
//        this.limitSetDate = cardDTO.getLimitSetDate();
//        this.issuedDate = cardDTO.getIssuedDate();
//        this.activeDate = cardDTO.getActiveDate();
//        this.userCard = cardDTO.getUserCard();
//        this.userActive = cardDTO.getUserActive();
//    }
}
