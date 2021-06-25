package com.gasstation.managementsystem.model.dto.api;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ApiDTOCreate {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Method is manda")
    private String method;
    @NotBlank(message = "Method is manda")
    private String api;
    @NotNull(message = "User type list is mandatory")
    private List<Integer> userTypeList;
}
