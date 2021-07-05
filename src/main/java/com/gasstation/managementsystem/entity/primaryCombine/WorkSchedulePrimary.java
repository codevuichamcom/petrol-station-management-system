package com.gasstation.managementsystem.entity.primaryCombine;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class WorkSchedulePrimary implements Serializable {
    private int employeeId;
    private int shiftId;
}
