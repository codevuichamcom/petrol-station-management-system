package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
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

    @Column(nullable = false, unique = true)
    private String driverPhone;

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private String licensePalates;

    @Column(nullable = false)
    private double initialDebt = 0;
    @Column(nullable = false)
    private double availableBalance = 0;
    @Column(nullable = false)
    private double outstandingBalance = 0;

    @Column(nullable = false)
    private Date limitSetDate = new Date();

    @Column(nullable = false)
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
