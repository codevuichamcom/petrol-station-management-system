package com.gasstation.managementsystem.entity.primaryCombine;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class WorkSchedulePrimary implements Serializable {
    private int employeeId;
    private int shiftId;
}
