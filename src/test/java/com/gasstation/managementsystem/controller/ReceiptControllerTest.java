//package com.gasstation.managementsystem.controller;
//
//import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
//import com.gasstation.managementsystem.model.dto.card.CardDTO;
//import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
//import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
//import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
//import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTO;
//import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
//import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;
//import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
//import com.gasstation.managementsystem.model.dto.station.StationDTO;
//import com.gasstation.managementsystem.model.dto.tank.TankDTO;
//import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
//import com.gasstation.managementsystem.model.dto.user.UserDTO;
//import com.gasstation.managementsystem.service.ReceiptService;
//import com.gasstation.managementsystem.utils.DateTimeHelper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class ReceiptControllerTest {
//    @Mock
//    private ReceiptService receiptService;
//
//    @InjectMocks
//    ReceiptController receiptController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAll() {
//        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
//        UserDTO customer = UserDTO.builder()
//                .id(1)
//                .name("customer").build();
//        CardDTO cardDTO = CardDTO.builder()
//                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .customer(customer)
//                .availableBalance(100d)
//                .accountsPayable(20d)
//                .build();
//        StationDTO stationDTO = StationDTO.builder()
//                .id(1)
//                .name("Hoang Long")
//                .address("Me Linh- Ha Noi")
//                .longitude(39d)
//                .latitude(46d)
//                .build();
//        FuelDTO fuelDTO = FuelDTO.builder()
//                .id(23)
//                .name("E92")
//                .unit("litter")
//                .price(32460d)
//                .type("TYPE_E92").build();
//        TankDTO tankDTO = TankDTO.builder()
//                .id(100)
//                .name("tank_100")
//                .volume(900d)
//                .remain(20d)
//                .currentPrice(505500d)
//                .station(stationDTO)
//                .fuel(fuelDTO).build();
//        PumpDTO pumpDTO = PumpDTO.builder()
//                .id(333)
//                .name("Pump_333")
//                .tank(tankDTO)
//                .note("pump333").build();
//        PumpShiftDTO handOverShiftDTO = PumpShiftDTO.builder()
//                .id(1)
//                .shift(ShiftDTO.builder()
//                        .id(1)
//                        .name("shift")
//                        .build())
//                .pump(pumpDTO)
//                .build();
//        TransactionDTO transactionDTO = TransactionDTO.builder()
//                .id(1)
//                .time(time)
//                .volume(123d)
//                .uuid("123456")
//                .card(cardDTO)
//                .handOverShift(handOverShiftDTO)
//                .unitPrice(50500d).build();
//        Long createdDate = 16094340000000L;
//        List<ReceiptDTO> receiptDTOList = IntStream.range(1, 10).mapToObj(i -> ReceiptDTO.builder()
//                .card(CardDTO.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                .createdDate(createdDate)
//                .amount(100d)
//                .creator(UserDTO.builder().id(1).build())
//                .reason("reason")
//                .debt(DebtDTO.builder().id(1)
//                        .transaction(transactionDTO)
//                        .build())
//                .build()).collect(Collectors.toList());
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("data", receiptDTOList);
//        Mockito.when(receiptService.findAll()).thenReturn(map);
//        HashMap<String, Object> mapResult = receiptController.getAll();
//
//        assertTrue(mapResult.containsKey("data"));
//        List<ReceiptDTO> receiptDTOSListResult = (List<ReceiptDTO>) mapResult.get("data");
//        assertEquals(receiptDTOList.size(), receiptDTOSListResult.size());
//        for (int i = 0; i < receiptDTOSListResult.size(); i++) {
//            ReceiptDTO o1 = receiptDTOList.get(i);
//            ReceiptDTO o2 = receiptDTOSListResult.get(i);
//            assertEquals(o1, o2);
//        }
//    }
//
//    @Test
//    void getOne() throws CustomNotFoundException {
//        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
//        UserDTO customer = UserDTO.builder()
//                .id(1)
//                .name("customer").build();
//        CardDTO cardDTO = CardDTO.builder()
//                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .customer(customer)
//                .availableBalance(100d)
//                .accountsPayable(20d)
//                .build();
//        StationDTO stationDTO = StationDTO.builder()
//                .id(1)
//                .name("Hoang Long")
//                .address("Me Linh- Ha Noi")
//                .longitude(39d)
//                .latitude(46d)
//                .build();
//        FuelDTO fuelDTO = FuelDTO.builder()
//                .id(23)
//                .name("E92")
//                .unit("litter")
//                .price(32460d)
//                .type("TYPE_E92").build();
//        TankDTO tankDTO = TankDTO.builder()
//                .id(100)
//                .name("tank_100")
//                .volume(900d)
//                .remain(20d)
//                .currentPrice(505500d)
//                .station(stationDTO)
//                .fuel(fuelDTO).build();
//        PumpDTO pumpDTO = PumpDTO.builder()
//                .id(333)
//                .name("Pump_333")
//                .tank(tankDTO)
//                .note("pump333").build();
//        PumpShiftDTO handOverShiftDTO = PumpShiftDTO.builder()
//                .id(1)
//                .shift(ShiftDTO.builder()
//                        .id(1)
//                        .name("shift")
//                        .build())
//                .pump(pumpDTO)
//                .build();
//        TransactionDTO transactionDTO = TransactionDTO.builder()
//                .id(1)
//                .time(time)
//                .volume(123d)
//                .uuid("123456")
//                .card(cardDTO)
//                .handOverShift(handOverShiftDTO)
//                .unitPrice(50500d).build();
//        Long createdDate = 16094340000000L;
//        ReceiptDTO mockResult = ReceiptDTO.builder()
//                .id(1)
//                .card(CardDTO.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                .createdDate(createdDate)
//                .amount(100d)
//                .creator(UserDTO.builder().id(1).build())
//                .reason("reason")
//                .debt(DebtDTO.builder().id(1)
//                        .transaction(transactionDTO)
//                        .build())
//                .build();
//        Mockito.when(receiptService.findById(1)).thenReturn(mockResult);
//        assertEquals(mockResult, receiptController.getOne(1));
//    }
//
//    @Test
//    void create() throws CustomNotFoundException {
//        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
//        UserDTO customer = UserDTO.builder()
//                .id(1)
//                .name("customer").build();
//        CardDTO cardDTO = CardDTO.builder()
//                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .customer(customer)
//                .availableBalance(100d)
//                .accountsPayable(20d)
//                .build();
//        StationDTO stationDTO = StationDTO.builder()
//                .id(1)
//                .name("Hoang Long")
//                .address("Me Linh- Ha Noi")
//                .longitude(39d)
//                .latitude(46d)
//                .build();
//        FuelDTO fuelDTO = FuelDTO.builder()
//                .id(23)
//                .name("E92")
//                .unit("litter")
//                .price(32460d)
//                .type("TYPE_E92").build();
//        TankDTO tankDTO = TankDTO.builder()
//                .id(100)
//                .name("tank_100")
//                .volume(900d)
//                .remain(20d)
//                .currentPrice(505500d)
//                .station(stationDTO)
//                .fuel(fuelDTO).build();
//        PumpDTO pumpDTO = PumpDTO.builder()
//                .id(333)
//                .name("Pump_333")
//                .tank(tankDTO)
//                .note("pump333").build();
//        PumpShiftDTO handOverShiftDTO = PumpShiftDTO.builder()
//                .id(1)
//                .shift(ShiftDTO.builder()
//                        .id(1)
//                        .name("shift")
//                        .build())
//                .pump(pumpDTO)
//                .build();
//        TransactionDTO transactionDTO = TransactionDTO.builder()
//                .id(1)
//                .time(time)
//                .volume(123d)
//                .uuid("123456")
//                .card(cardDTO)
//                .handOverShift(handOverShiftDTO)
//                .unitPrice(50500d).build();
//        Long createdDate = 16094340000000L;
//        ReceiptDTO mockResult = ReceiptDTO.builder()
//                .id(1)
//                .card(CardDTO.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                .createdDate(createdDate)
//                .amount(100d)
//                .creator(UserDTO.builder().id(1).build())
//                .reason("reason")
//                .debt(DebtDTO.builder().id(1)
//                        .transaction(transactionDTO)
//                        .build())
//                .build();
//        ReceiptDTOCreate receiptDTOCreate = ReceiptDTOCreate.builder()
//                .cardId(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .amount(100d)
//                .reason("reason")
//                .debtId(1)
//                .build();
//        Mockito.when(receiptService.create(receiptDTOCreate)).thenReturn(mockResult);
//        assertEquals(mockResult, receiptController.create(receiptDTOCreate));
//    }
//}