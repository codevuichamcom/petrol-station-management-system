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

    @Schema(example = "123456789",description = "Identity card number is composed of 9 or 12 digits")
    @NotBlank(message = "Identity card number is mandatory")
    @Pattern(regexp = "^[0-9]{9}|[0-9]{12}$", message = "Identity card number is composed of 9 or 12 digits")
    private String identityCardNumber;//số chứng minh nhân dân

    @Schema(example = "Lê Hồng Quân")
    @NotBlank(message = "Name is mandatory")
    private String name;

    private boolean gender = false;

    @Schema(description = "Must be in past")
    @Past(message = "Must be in past")
    private Date dateOfBirth;

    private String address;

    @Pattern(regexp = "^[0-9]+$", message = "Phone just include digit")
    private String phone;

    @Email(message = "Field must be email")
    @Schema(description = "Field must be email", example = "quan@gmail.com")
    private String email;

    private String note;

    private double cashLimit = 0;

    @Schema(description = "Must be present in future")
    @FutureOrPresent(message = "Must be present in future")
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
