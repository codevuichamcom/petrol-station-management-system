package com.gasstation.managementsystem.model.dto.priceChangeHistory;

import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PriceChangeHistoryDTO {
    private int id;
    private long time;
    private Double oldPrice;
    private Double newPrice;
    private UserDTO editor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceChangeHistoryDTO that = (PriceChangeHistoryDTO) o;
        return id == that.id && time == that.time && Objects.equals(oldPrice, that.oldPrice) && Objects.equals(newPrice, that.newPrice) && Objects.equals(editor, that.editor);
    }
}
