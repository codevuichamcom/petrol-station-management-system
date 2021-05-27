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
    private String name;
    private String address;

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
    @JoinTable(name = "user_staton_tbl", joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<User> employeeList;

//
//    public Station(StationDTO stationDTO) {
//        this.id = stationDTO.getId();
//        this.user = stationDTO.getUser();
//        this.tanks = stationDTO.getTanks();
//        this.address = stationDTO.getAddress();
//        this.tanks = stationDTO.getTanks();
//    }
}
