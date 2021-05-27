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
    private double volume;
    private double remain;

    @OneToMany(mappedBy = "tank", cascade = CascadeType.ALL)
    private List<FuelImportBill> fuelImportBillList; //Danh sách phiếu nhập của bể này

    @ManyToOne
    @JoinColumn(name = "fuel_category_id") //join với fuel_category_tbl
    private FuelCategory fuelCategory;// Bể này thuộc thể loại nhiên liệu là gì

    @ManyToOne
    @JoinColumn(name = "station_id")//join với station_tbl
    private Station station; //Bể này của Trạm nào

    @OneToMany(mappedBy = "tank", cascade = CascadeType.ALL)
    private List<Pump> pumpList;//Danh sách vòi bơm của bể này

    @OneToMany(mappedBy = "tank", cascade = CascadeType.ALL)
    private List<PriceChangeHistory> priceChangeHistoryList;// Danh sách những lần đổ giá của bể này

//    public Tank(TankDTO tankDTO) {
//        this.id = tankDTO.getId();
//        this.volume = tankDTO.getVolume();
//        this.remain = tankDTO.getRemain();
//        this.productCategory = tankDTO.getProductCategory();
//        this.station = tankDTO.getStation();
//    }
}
