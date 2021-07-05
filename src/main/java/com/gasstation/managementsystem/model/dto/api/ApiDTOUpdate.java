package com.gasstation.managementsystem.model.dto.api;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ApiDTOUpdate {
    private List<Integer> accessibleUserTypes;
}
