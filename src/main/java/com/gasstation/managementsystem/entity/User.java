package com.gasstation.managementsystem.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
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

    @Column(unique = true, nullable = false)
    private String identityCardNumber;//số chứng minh nhân dân

    private String name;

    private boolean gender;

    private Date dateOfBirth;

    private String address;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    private String note;

    private double cashLimit;

    private Date limitSetDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PriceChangeHistory> priceChangeHistoryList;//Danh sách những giá do người dùng này đổi

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Station> stationList;//Danh sách các trạm của người này

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Shift> shiftList;//Danh sách các ca bơm của người này(nhân viên này)

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Shift> shiftListManageByMe;//Danh sách ca bơm thuộc sự quản lý của người này(owner này)

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<PaymentBill> paymentBillList;//Danh sacsh hóa đơn thanh toán do người này tạo

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Payment> paymentList;//Danh sách thanh toán của người dùng này

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<ReceiptBill> receiptBillList;//Danh sách hóa đơn nhận của người này

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private Account account;//Tài khoản của user này

    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @OneToMany(mappedBy = "userActive", cascade = CascadeType.ALL)
    private List<Card> cardListActiveByMe;//Danh sách active bởi tài khoản này

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<Card> cardList;//Danh sách thẻ của người này

    @ManyToMany(mappedBy = "employeeList")
    private List<Station> stationListOfEmployee; //Danh sách các trạm của nhân viên này


//    public User(UserDTO userDTO) {
//        this.id = userDTO.getId();
//        this.fullName = userDTO.getFullName();
//        this.address = userDTO.getAddress();
//        this.phone = userDTO.getPhone();
//        this.email = userDTO.getEmail();
//        this.note = userDTO.getNote();
//        this.cashLimit = userDTO.getCashLimit();
//        this.limitSetDate = userDTO.getLimitSetDate();
//        this.account = userDTO.getAccount();
//        this.cards = userDTO.getCards();
//        this.cardsActive = userDTO.getCardsActive();
//        this.station = userDTO.getStation();
//    }
}
