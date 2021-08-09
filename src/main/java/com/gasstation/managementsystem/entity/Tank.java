package com.gasstation.managementsystem.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tank_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "station_id"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Tank extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double volume;

    @Column(nullable = false)
    private Double remain;

    @Column(nullable = false)
    private Double currentPrice;

    @OneToMany(mappedBy = "tank")
    private List<FuelImport> fuelImportList; //Danh sách phiếu nhập của bể này

    @ManyToOne
    @JoinColumn(name = "fuel_id", nullable = false) //join với fuel_tbl
    private Fuel fuel;// Bể này chứa nhiên liệu gì

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)//join với station_tbl
    private Station station; //Bể này của Trạm nào

    @OneToMany(mappedBy = "tank")
    private List<Pump> pumpList;//Danh sách vòi bơm của bể này

    @OneToMany(mappedBy = "tank")
    private List<PriceChangeHistory> priceChangeHistoryList;// Danh sách những lần đổ giá của bể này

}
