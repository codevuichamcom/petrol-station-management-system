package com.gasstation.managementsystem.entity;

import com.gasstation.managementsystem.utils.DateTimeHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "price_change_history_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PriceChangeHistory extends BaseEntity {

    @Column(nullable = false)
    private Long time = DateTimeHelper.getCurrentUnixTime();

    @Column(nullable = false)
    private Double oldPrice;

    @Column(nullable = false)
    private Double newPrice;

    @ManyToOne
    @JoinColumn(name = "editor_id", nullable = false)
    private User editor;//người dùng nào đổi giá

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station; //Trạm nào đổi giá

    @ManyToOne
    @JoinColumn(name = "tank_id", nullable = false)
    private Tank tank; //Đổi giá tại bể nào

}
