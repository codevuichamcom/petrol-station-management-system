package com.gasstation.managementsystem.model.dto.api;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ApiDTOUpdate {
    private String name;

    private String method;

    private String api;

    private List<Integer> userTypeList;
}
