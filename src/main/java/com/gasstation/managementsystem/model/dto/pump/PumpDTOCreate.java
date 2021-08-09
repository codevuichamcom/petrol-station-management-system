package com.gasstation.managementsystem.model.dto.pump;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PumpDTOCreate {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String note;
    @NotNull(message = "Tank id is mandatory")
    private Integer tankId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PumpDTOCreate that = (PumpDTOCreate) o;
        return Objects.equals(name, that.name) && Objects.equals(note, that.note) && Objects.equals(tankId, that.tankId);
    }
}
