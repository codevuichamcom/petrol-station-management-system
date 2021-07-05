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
@ToString
public class ApiDTOCreate {
    @NotBlank(message = "Method is mandatory")
    private String method;
    @NotBlank(message = "Path is mandatory")
    private String path;
    @NotNull(message = "Accessible User Type list is mandatory")
    @ToString.Exclude()
    private List<Integer> accessibleUserTypes;
}
