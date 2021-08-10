package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.card.CardDTOCreate;
import com.gasstation.managementsystem.model.dto.card.CardDTOFilter;
import com.gasstation.managementsystem.model.dto.card.CardDTOUpdate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.service.impl.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardControllerTest {
    @Mock
    CardServiceImpl cardService;

    @InjectMocks
    CardController cardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param pageSize is not null
     */
    @Test
    void getAll_UTCID01() {
        List<CardDTO> cardDTOList = IntStream.range(1, 9).mapToObj(i -> CardDTO.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7" + i))
                .driverPhone("0123456789")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .initialDebt(100d)
                .availableBalance(1000d)
                .accountsPayable(200d)
                .debtLimit(5000d)
                .limitSetDate(16094340000000L)
                .createdDate(16094440000000L)
                .active(true)
                .creator(UserDTO.builder().id(1).name("admin").build())
                .customer(UserDTO.builder().id(2).name("customer").build())
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", cardDTOList);

        final int PAGE_INDEX = 1;
        final int PAGE_SIZE = 3;
        Integer[] shiftIds = new Integer[1];
        shiftIds[0] = 1;
        Integer[] pumpIds = new Integer[1];
        pumpIds[0] = 1;
        Integer[] stationIds = new Integer[1];
        stationIds[0] = 1;
        String[] statuses = new String[1];
        statuses[0] = "status1";
        CardDTOFilter filter = CardDTOFilter.builder()
                .pageIndex(PAGE_INDEX)
                .pageSize(PAGE_SIZE)
                .accountsPayable(100d)
                .availableBalance(200d)
                .statuses(statuses)
                .createdDate(16094340000000L)
                .customerName("customerName")
                .customerPhone("0123456789")
                .licensePlate("36B1-75097").build();

        Mockito.when(cardService.findAll(filter)).thenReturn(map);

        HashMap<String, Object> mapResult = cardController.getAll(PAGE_INDEX, PAGE_SIZE,
                100d, 200d, statuses,
                16094340000000L, "customerName", "0123456789", null);


//        List<CardDTO> cardDTOListResult = (List<CardDTO>) mapResult.get("data");
//        assertEquals(cardDTOList.size(), cardDTOListResult.size());
//        for (int i = 0; i < cardDTOListResult.size(); i++) {
//            CardDTO o1 = mapResult.get(i);
//            CardDTO o2 = cardDTOListResult.get(i);
//            assertEquals(o1, o2);
//        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        CardDTO mockResult = CardDTO.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .driverPhone("0123456789")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .initialDebt(100d)
                .availableBalance(1000d)
                .accountsPayable(200d)
                .debtLimit(5000d)
                .limitSetDate(16094340000000L)
                .createdDate(16094440000000L)
                .active(true)
                .creator(UserDTO.builder().id(1).name("admin").build())
                .customer(UserDTO.builder().id(2).name("customer").build())
                .build();
        Mockito.when(cardService.findById(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(mockResult);
        assertEquals(mockResult, cardController.getOne(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")));
    }

    @Test
    void create() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long limitSetDate = 16094340000000L;
        Long createdDate = 16094440000000L;
        Long activeDate = 16094340000000L;
        CardDTO mockResult = CardDTO.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .driverPhone("0123456789")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .initialDebt(100d)
                .availableBalance(1000d)
                .accountsPayable(200d)
                .debtLimit(5000d)
                .limitSetDate(16094340000000L)
                .createdDate(16094440000000L)
                .active(true)
                .creator(UserDTO.builder().id(1).name("admin").build())
                .customer(UserDTO.builder().id(2).name("customer").build())
                .build();
        CardDTOCreate cardDTOCreate = CardDTOCreate.builder()
                .driverPhone("0123456789")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .debtLimit(5000d)
                .customerId(1)
                .build();
        Mockito.when(cardService.create(cardDTOCreate)).thenReturn(mockResult);
        assertEquals(mockResult, cardController.create(cardDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long limitSetDate = 16094340000000L;
        Long createdDate = 16094440000000L;
        Long activeDate = 16094340000000L;
        CardDTO mockResult = CardDTO.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .driverPhone("0123456788")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .initialDebt(1000d)
                .availableBalance(10000d)
                .accountsPayable(2000d)
                .debtLimit(50000d)
                .limitSetDate(16094340000000L)
                .createdDate(16094440000000L)
                .active(true)
                .creator(UserDTO.builder().id(1).name("admin").build())
                .customer(UserDTO.builder().id(2).name("customer").build())
                .build();

        CardDTOUpdate cardDTOUpdate = CardDTOUpdate.builder()
                .driverPhone("0123456788")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .initialDebt(1000d)
                .payInAmount(10000d)
                .debtLimit(50000d)
                .build();
        Mockito.when(cardService.update(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"), cardDTOUpdate)).thenReturn(mockResult);
        assertEquals(mockResult, cardController.update(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"), cardDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        CardDTO mockResult = CardDTO.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .driverPhone("0123456788")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .initialDebt(1000d)
                .availableBalance(10000d)
                .accountsPayable(2000d)
                .debtLimit(50000d)
                .limitSetDate(16094340000000L)
                .createdDate(16094440000000L)
                .active(true)
                .creator(UserDTO.builder().id(1).name("admin").build())
                .customer(UserDTO.builder().id(2).name("customer").build())
                .build();
        Mockito.when(cardService.delete(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(mockResult);
        assertEquals(mockResult, cardController.delete(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")));
    }
}