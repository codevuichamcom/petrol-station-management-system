package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String identityCardNumber;//số chứng minh nhân dân

    @Column(nullable = false)
    private String name;

    private boolean gender = false;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String address;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(unique = true)
    private String email;

    private String note;

    private boolean active = false;

    @OneToMany(mappedBy = "editor")
    private List<PriceChangeHistory> priceChangeHistoryList;//Danh sách những giá do người dùng này đổi

    @OneToMany(mappedBy = "owner")
    private List<Station> stationList;//Danh sách các trạm của người này

    @OneToMany(mappedBy = "creator")
    private List<ReceiptBill> receiptBillList;//Danh sách hóa đơn nhận của người này

    @ManyToOne
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "customer")
    private List<Card> cardListActiveByMe;//Danh sách active bởi tài khoản này

    @OneToMany(mappedBy = "customer")
    private List<Card> cardList;//Danh sách thẻ của người này

    @OneToMany(mappedBy = "user")
    private List<RefreshToken> refreshTokenList = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<FuelImport> fuelImportList = new ArrayList<>();
}
