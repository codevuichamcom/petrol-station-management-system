package com.gasstation.managementsystem.model.dto.employee;

import com.gasstation.managementsystem.entity.WorkSchedule;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class EmployeeDTO {
    private int id;
    private String name;
    private String address;
    private String phone;
    private Boolean gender;
    private String dateOfBirth;
    private String identityCardNumber;
    private StationDTO station;
}
