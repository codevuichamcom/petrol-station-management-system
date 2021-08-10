package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.priceChangeHistory.PriceChangeHistoryDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PriceChangeHistoryMapperTest {
    /**
     * param priceChangeHistory is null
     */
    @Test
    void toPriceChangeHistoryDTO_UTCID01() {
        assertNull(PriceChangeHistoryMapper.toPriceChangeHistoryDTO(null));
    }

    /**
     * param priceChangeHistory is not null
     */
    @Test
    void toPriceChangeHistoryDTO_UTCID02() {
        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        PriceChangeHistory priceChangeHistory = PriceChangeHistory.builder()
                .id(1)
                .oldPrice(100d)
                .newPrice(200d)
                .time(time)
                .editor(User.builder().id(1).name("employee1").build())
                .build();
        PriceChangeHistoryDTO priceChangeHistoryDTO =
                PriceChangeHistoryMapper.toPriceChangeHistoryDTO(priceChangeHistory);
        //then
        assertEquals(priceChangeHistory.getId(), priceChangeHistoryDTO.getId());
        assertEquals(priceChangeHistory.getOldPrice(), priceChangeHistoryDTO.getOldPrice());
        assertEquals(priceChangeHistory.getNewPrice(), priceChangeHistoryDTO.getNewPrice());
        assertEquals(priceChangeHistory.getTime(), priceChangeHistoryDTO.getTime());
        assertEquals(priceChangeHistory.getEditor().getName(), priceChangeHistoryDTO.getEditor().getName());
        assertEquals(priceChangeHistory.getEditor().getId(), priceChangeHistoryDTO.getEditor().getId());
    }
}