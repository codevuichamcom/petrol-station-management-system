package com.gasstation.managementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "station_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    private Double longitude; //kinh độ

    private Double latitude;//vĩ độ


    @OneToMany(mappedBy = "station")
    private List<Tank> tankList;//Danh sách các bể của trạm này

    @OneToMany(mappedBy = "station")
    private List<PriceChangeHistory> priceChangeHistoryList;//Danh sách đổi giá của trạm này

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;//Chủ Tạm là ai

    @OneToMany(mappedBy = "station")
    private List<Debt> debtList;

    @OneToMany(mappedBy = "station")
    private List<Expense> expenseList; //Danh sách chi của trạm này

    @OneToMany(mappedBy = "station")
    private List<Employee> employeeList = new ArrayList<>();//danh sách nhân viên của trạm này
}
