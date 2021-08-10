package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.model.dto.priceChangeHistory.PriceChangeHistoryDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.service.impl.PriceChangeHistoryServiceImpl;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PriceChangeHistoryControllerTest {
    @Mock
    PriceChangeHistoryServiceImpl priceChangeHistoryService;

    @InjectMocks
    PriceChangeHistoryController priceChangeHistoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param pageSize is not null
     */

    @Test
    void getAll_UTCID01() {
        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        List<PriceChangeHistoryDTO> priceChangeHistoryDTOS = IntStream.range(1, 10).mapToObj(i -> PriceChangeHistoryDTO.builder()
                .id(i)
                .oldPrice(100d)
                .newPrice(200d)
                .time(time)
                .editor(UserDTO.builder().id(1).name("employee1").build())
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", priceChangeHistoryDTOS);
        final int PAGE_INDEX = 1;
        final int PAGE_SIZE = 3;
        Mockito.when(priceChangeHistoryService.findAllByTankId(1, PageRequest.of(PAGE_INDEX - 1, PAGE_SIZE, Sort.by(Sort.Direction.ASC, "id")))).thenReturn(map);
        HashMap<String, Object> mapResult = priceChangeHistoryController.getAll(PAGE_INDEX, PAGE_SIZE, 1);

        assertTrue(mapResult.containsKey("data"));
        List<PriceChangeHistoryDTO> priceChangeHistoryDTOSListResult = (List<PriceChangeHistoryDTO>) mapResult.get("data");
        assertEquals(priceChangeHistoryDTOS.size(), priceChangeHistoryDTOSListResult.size());
        for (int i = 0; i < priceChangeHistoryDTOSListResult.size(); i++) {
            PriceChangeHistoryDTO o1 = priceChangeHistoryDTOS.get(i);
            PriceChangeHistoryDTO o2 = priceChangeHistoryDTOSListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    /**
     * param pageSize is null
     */

    @Test
    void getAll_UTCID02() {
        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        List<PriceChangeHistoryDTO> fuelImportDTOList = IntStream.range(1, 10).mapToObj(i -> PriceChangeHistoryDTO.builder()
                .id(i)
                .oldPrice(100d)
                .newPrice(200d)
                .time(time)
                .editor(UserDTO.builder().id(1).name("employee1").build())
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelImportDTOList);
        final int PAGE_INDEX = 1;
        Mockito.when(priceChangeHistoryService.findAllByTankId(1)).thenReturn(map);
        HashMap<String, Object> mapResult = priceChangeHistoryController.getAll(PAGE_INDEX, null, 1);

        assertTrue(mapResult.containsKey("data"));
        List<PriceChangeHistoryDTO> priceChangeHistoryDTOSListResult = (List<PriceChangeHistoryDTO>) mapResult.get("data");
        assertEquals(fuelImportDTOList.size(), priceChangeHistoryDTOSListResult.size());
        for (int i = 0; i < priceChangeHistoryDTOSListResult.size(); i++) {
            PriceChangeHistoryDTO o1 = fuelImportDTOList.get(i);
            PriceChangeHistoryDTO o2 = priceChangeHistoryDTOSListResult.get(i);
            assertEquals(o1, o2);
        }
    }
}