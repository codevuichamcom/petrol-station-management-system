package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "price_change_history_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PriceChangeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @Column(nullable = false)
    private double price = 0;

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
