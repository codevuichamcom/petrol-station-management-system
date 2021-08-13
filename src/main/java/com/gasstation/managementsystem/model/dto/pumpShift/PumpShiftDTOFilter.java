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
    private Long createdDateFrom;
    private Long createdDateTo;
    private Long closedTimeFrom;
    private Long closedTimeTo;
    private String shiftName;
    private String pumpName;
    private Integer[] stationIds;
    private String stationName;
    private String[] statuses;

}
