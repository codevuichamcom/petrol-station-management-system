package com.gasstation.managementsystem.model.dto.employee;

import com.gasstation.managementsystem.entity.WorkSchedule;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import lombok.*;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(gender, that.gender) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(identityCardNumber, that.identityCardNumber) && Objects.equals(station, that.station);
    }

}
