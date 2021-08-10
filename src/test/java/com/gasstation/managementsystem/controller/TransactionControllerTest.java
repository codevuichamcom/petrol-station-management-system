//package com.gasstation.managementsystem.controller;
//
//import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
//import com.gasstation.managementsystem.model.dto.card.CardDTO;
//import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
//import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
//import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOFilter;
//import com.gasstation.managementsystem.model.dto.transaction.TransactionUuidDTO;
//import com.gasstation.managementsystem.model.dto.user.UserDTO;
//import com.gasstation.managementsystem.service.TransactionService;
//import com.gasstation.managementsystem.utils.DateTimeHelper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//class TransactionControllerTest {
//    @Mock
//    private TransactionService transactionService;
//
//    @InjectMocks
//    TransactionController transactionController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAll() {
//        UserDTO customerDTO = UserDTO.builder()
//                .id(1)
//                .name("customer").build();
//        CardDTO cardDTO = CardDTO.builder()
//                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .customer(customerDTO)
//                .build();
//        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
//
//        List<TransactionDTO> cardDTOList = IntStream.range(1, 9).mapToObj(i -> TransactionDTO.builder()
//                .id(123456)
//                .time(time)
//                .volume(123d)
//                .uuid("123456")
//                .card(cardDTO)
//                .unitPrice(50500d)
//                .build()).collect(Collectors.toList());
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("data", cardDTOList);
//
//        final int PAGE_INDEX = 1;
//        final int PAGE_SIZE = 3;
//        Integer[] shiftIds = new Integer[1];
//        shiftIds[0] = 1;
//        Integer[] pumpIds = new Integer[1];
//        pumpIds[0] = 1;
//        Integer[] stationIds = new Integer[1];
//        stationIds[0] = 1;
//        String[] statuses = new String[1];
//        statuses[0] = "status1";
//        TransactionDTOFilter transactionDTOFilter = TransactionDTOFilter.builder()
//                .pageIndex(PAGE_INDEX)
//                .pageSize(PAGE_SIZE)
//                .pumpIds(pumpIds)
//                .shiftIds(shiftIds)
//                .stationIds(stationIds)
//                .time(time)
//                .unitPrice(100d)
//                .volume(2000d).build();
//
//        Mockito.when(transactionService.findAll(transactionDTOFilter)).thenReturn(map);
//
//        HashMap<String, Object> mapResult = transactionController.getAll(PAGE_INDEX, PAGE_SIZE,
//                pumpIds, shiftIds, stationIds,
//                time, 100d, 2000d);
//    }
//
//    @Test
//    void create() throws CustomNotFoundException {
//        UserDTO customerDTO = UserDTO.builder()
//                .id(1)
//                .name("customer").build();
//        CardDTO cardDTO = CardDTO.builder()
//                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .customer(customerDTO)
//                .build();
//        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
//
//        List<TransactionDTO> mockResult = IntStream.range(1, 9).mapToObj(i -> TransactionDTO.builder()
//                .id(123456)
//                .time(time)
//                .volume(123d)
//                .uuid("123456")
//                .card(cardDTO)
//                .unitPrice(50500d)
//                .build()).collect(Collectors.toList());
//        TransactionDTOCreate transactionDTOCreate = TransactionDTOCreate.builder()
//                .time(time)
//                .pumpId(1)
//                .volume(123d)
//                .uuid("123456")
//                .cardId(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .unitPrice(50500d).build();
//        List<TransactionDTOCreate> transactionDTOCreateList = new ArrayList<TransactionDTOCreate>();
//        transactionDTOCreateList.add(transactionDTOCreate);
//        List<TransactionUuidDTO> transactionUuidDTOS = new ArrayList<>();
//        Mockito.when(transactionService.create(transactionDTOCreateList)).thenReturn(transactionUuidDTOS);
//        transactionController.create(transactionDTOCreateList);
//    }
//}