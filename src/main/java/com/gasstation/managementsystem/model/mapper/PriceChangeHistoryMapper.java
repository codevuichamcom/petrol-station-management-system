package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.priceChangeHistory.PriceChangeHistoryDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;

public class PriceChangeHistoryMapper {

    public static PriceChangeHistoryDTO toPriceChangeHistoryDTO(PriceChangeHistory priceChangeHistory) {
        if (priceChangeHistory == null) return null;
        User editor = priceChangeHistory.getEditor();
        UserDTO editorDTO = editor != null ? UserDTO.builder().id(editor.getId()).name(editor.getName()).build() : null;
        return PriceChangeHistoryDTO.builder()
                .id(priceChangeHistory.getId())
                .time(priceChangeHistory.getTime())
                .oldPrice(priceChangeHistory.getOldPrice())
                .newPrice(priceChangeHistory.getNewPrice())
                .editor(editorDTO)
                .build();
    }
}
