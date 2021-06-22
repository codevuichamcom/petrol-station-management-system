package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "station_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private Double longitude; //kinh độ

    private Double latitude;//vĩ độ


    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<Tank> tankList;//Danh sách các bể của trạm này

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<PriceChangeHistory> priceChangeHistoryList;//Danh sách đổi giá của trạm này

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;//Chủ Tạm là ai

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<Shift> shiftList;//Danh Sách ca bơm của Trạm này

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_station_tbl", joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<User> employeeList;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<Debt> debtList;

}
