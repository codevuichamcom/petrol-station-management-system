package com.gasstation.managementsystem.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String identityCardNumber;//số chứng minh nhân dân

    @Column(nullable = false)
    private String name;

    private Boolean gender;

    @Column(nullable = false)
    private Long dateOfBirth;

    @Column(nullable = false)
    private String address;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(unique = true)
    private String email;

    private String note;

    private Boolean active;

    @OneToMany(mappedBy = "editor")
    private List<PriceChangeHistory> priceChangeHistoryList;//Danh sách những giá do người dùng này đổi

    @OneToMany(mappedBy = "owner")
    private List<Station> stationList;//Danh sách các trạm của người này

    @OneToMany(mappedBy = "creator")
    private List<Receipt> receiptList;//Danh sách hóa đơn nhận do người này tạo

    @OneToMany(mappedBy = "creator")
    private List<Expense> expenseList;//danh sách chi phí do người này tạo
    @ManyToOne
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "customer")
    private List<Card> cardList;//Danh sách thẻ của người này

    @OneToMany(mappedBy = "user")
    private List<RefreshToken> refreshTokenList = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<FuelImport> fuelImportList = new ArrayList<>();

    @OneToMany(mappedBy = "executor")
    private List<PumpShift> pumpShiftList = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<Card> cardListCreatorByMe = new ArrayList<>();
}
