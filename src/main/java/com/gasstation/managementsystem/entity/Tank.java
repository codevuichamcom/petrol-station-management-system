package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tank_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Tank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double volume = 0;

    @Column(nullable = false)
    private double remain = 0;

    @Column(nullable = false)
    private double curentPrice = 0;

    @OneToMany(mappedBy = "tank", cascade = CascadeType.ALL)
    private List<FuelImportBill> fuelImportBillList; //Danh sách phiếu nhập của bể này

    @ManyToOne
    @JoinColumn(name = "fuel_id") //join với fuel_tbl
    private Fuel fuel;// Bể này chứa nhiên liệu gì

    @ManyToOne
    @JoinColumn(name = "station_id")//join với station_tbl
    private Station station; //Bể này của Trạm nào

    @OneToMany(mappedBy = "tank", cascade = CascadeType.ALL)
    private List<Pump> pumpList;//Danh sách vòi bơm của bể này

    @OneToMany(mappedBy = "tank", cascade = CascadeType.ALL)
    private List<PriceChangeHistory> priceChangeHistoryList;// Danh sách những lần đổ giá của bể này

}
