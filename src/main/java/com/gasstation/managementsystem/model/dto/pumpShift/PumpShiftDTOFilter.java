package com.gasstation.managementsystem.model.dto.pumpShift;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PumpShiftDTOFilter {
    public static final String STATUS_CLOSED = "CLOSED";
    public static final String STATUS_UNCLOSE = "UNCLOSE";
    public static final String STATUS_WORKING = "WORKING";
    public static final String STATUS_FUTURE = "FUTURE";
    private Integer pageIndex;
    private Integer pageSize;
    private Long createdDate;
    private Long closedTime;
    private Integer[] shiftIds;
    private Integer[] pumpIds;
    private Integer[] stationIds;
    private String executorName;
    private String[] statuses;

}
