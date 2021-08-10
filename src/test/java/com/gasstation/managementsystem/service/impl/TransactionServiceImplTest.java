package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.exception.custom.CustomInternalServerException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOFilter;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.mapper.TransactionMapper;
import com.gasstation.managementsystem.repository.*;
import com.gasstation.managementsystem.repository.criteria.PumpShiftRepositoryCriteria;
import com.gasstation.managementsystem.repository.criteria.TransactionRepositoryCriteria;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private TransactionRepositoryCriteria transactionCriteria;
    @Mock
    private OptionalValidate optionalValidate;
    @Mock
    private PumpRepository pumpRepository;
    @Mock
    private PumpShiftRepository pumpShiftRepository;
    @Mock
    private DebtRepository debtRepository;
    @Mock
    private CardRepository cardRepository;
    @Mock
    private PumpShiftRepositoryCriteria handOverShiftCriteria;
    @Mock
    private ShiftRepository shiftRepository;
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * have param
     */
    @Test
    void findAll() {
        List<Transaction> mockRepository = new ArrayList<>();
        List<TransactionDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);

        TransactionDTOFilter transactionDTOFilter = TransactionDTOFilter.builder().pageIndex(1).pageSize(3).build();
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("totalElement", 1);
        temp.put("data", mockRepository);
        temp.put("totalPage", 3);
        Mockito.when(transactionCriteria.findAll(transactionDTOFilter)).thenReturn(temp);
        List<TransactionDTO> listResultService = (List<TransactionDTO>) transactionService.
                findAll(transactionDTOFilter).get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            TransactionDTO o1 = mockResult.get(i);
            TransactionDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<Transaction> mockRepository, List<TransactionDTO> mockResult) {
        //given
        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        User customer = User.builder()
                .id(1)
                .name("customer").build();

        Card card = Card.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .customer(customer)
                .build();

        Station station = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        Fuel fuel = Fuel.builder()
                .id(23)
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();
        Tank tank = Tank.builder()
                .id(100)
                .name("tank_100")
                .volume(900d)
                .remain(20d)
                .currentPrice(505500d)
                .station(station)
                .fuel(fuel).build();
        Pump pump = Pump.builder()
                .id(333)
                .name("Pump_333")
                .tank(tank)
                .note("pump333").build();
        for (int i = 1; i <= 10; i++) {
            Transaction transaction = Transaction.builder()
                    .id(1)
                    .time(time)
                    .volume(123d)
                    .uuid("123456")
                    .card(card)
                    .pumpShift(PumpShift.builder()
                            .id(1)
                            .shift(Shift.builder()
                                    .id(1)
                                    .name("shift")
                                    .build())
                            .pump(pump)
                            .build())
                    .unitPrice(50500d).build();
            mockRepository.add(transaction);
            mockResult.add(TransactionMapper.toTransactionDTO(transaction));
        }
    }


    /**
     * param Hand over shift is exist && accountsPayable > 0
     */
    @Test
    void create_UTCID01() throws CustomNotFoundException, CustomInternalServerException {
        //given
        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        User customer = User.builder()
                .id(1)
                .name("customer").build();
        UserDTO customerDTO = UserDTO.builder()
                .id(1)
                .name("customer").build();
        Card card = Card.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .customer(customer)
                .availableBalance(100d)
                .accountsPayable(20d)
                .build();
        CardDTO cardDTO = CardDTO.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .customer(customerDTO)
                .build();
        Station station = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        Fuel fuel = Fuel.builder()
                .id(23)
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();
        StationDTO stationDTO = StationDTO.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        FuelDTO fuelDTO = FuelDTO.builder()
                .id(23)
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();
        Tank tank = Tank.builder()
                .id(100)
                .name("tank_100")
                .volume(900d)
                .remain(20d)
                .currentPrice(505500d)
                .station(station)
                .fuel(fuel).build();
        TankDTO tankDTO = TankDTO.builder()
                .id(100)
                .name("tank_100")
                .volume(900d)
                .remain(20d)
                .currentPrice(505500d)
                .station(stationDTO)
                .fuel(fuelDTO).build();
        Pump pump = Pump.builder()
                .id(333)
                .name("Pump_333")
                .tank(tank)
                .note("pump333").build();
        PumpDTO pumpDTO = PumpDTO.builder()
                .id(333)
                .name("Pump_333")
                .tank(tankDTO)
                .note("pump333").build();
        PumpShift pumpShift = PumpShift.builder()
                .id(1)
                .shift(Shift.builder()
                        .id(1)
                        .name("shift")
                        .build())
                .pump(pump)
                .build();
        PumpShift pumpShiftToday = PumpShift.builder()
                .id(100)
                .shift(Shift.builder()
                        .id(1)
                        .name("shift")
                        .build())
                .pump(pump)
                .build();
        Transaction mockRepository = Transaction.builder()
                .id(1)
                .time(time)
                .volume(123d)
                .uuid("123456")
                .card(card)
                .pumpShift(pumpShift)
                .unitPrice(50500d).build();

        TransactionDTOCreate transactionDTOCreate = TransactionDTOCreate.builder()
                .time(time)
                .pumpId(1)
                .volume(123d)
                .uuid("123456")
                .cardId(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .unitPrice(50500d).build();

        TransactionDTO mockResult = TransactionDTO.builder()
                .id(123456)
                .time(time)
                .volume(123d)
                .uuid("123456")
                .card(cardDTO)
                .unitPrice(50500d).build();
        List<TransactionDTOCreate> transactionDTOCreateList = new ArrayList<TransactionDTOCreate>();
        LocalDateTime localDateTime = DateTimeHelper.toDateTime(transactionDTOCreate.getTime());
        LocalDate localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
        transactionDTOCreateList.add(transactionDTOCreate);
        long milliSeconds = (localDateTime.getHour() * 3600 + localDateTime.getMinute() * 60 + localDateTime.getSecond()) * 1000;

        Debt debt = Debt.builder().accountsPayable(100d).transaction(mockRepository).build();
        Mockito.when(debtRepository.save(Mockito.any(Debt.class))).thenReturn(debt);
//        Mockito.when(handOverShiftCriteria.getHandOverShiftToday()).thenReturn(null);
        Mockito.when(cardRepository.save(Mockito.any(Card.class))).thenReturn(card);
        Mockito.when(optionalValidate.getCardById(UUID.
                fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(card);
        Mockito.when(optionalValidate.getPumpShiftByPumpIdNotClose
                (1, LocalDateTime.of(localDate, LocalTime.MIN)
                        .atZone(TimeZone.getDefault()
                                .toZoneId()).toEpochSecond() * 1000, milliSeconds)).thenReturn(pumpShiftToday);
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(mockRepository);
        List<Shift> shiftList = new ArrayList<>();
        shiftList.add(Shift.builder().id(1).build());
        Mockito.when(shiftRepository.findAllShiftByStationId(1)).thenReturn(shiftList);
        List<Pump> pumpList = new ArrayList<>();
        pumpList.add(pump);
        Mockito.when(pumpRepository.findAll()).thenReturn(pumpList);
        ArrayList<PumpShift> pumpShifts = new ArrayList<>();
        pumpShifts.add(pumpShift);
        Mockito.when(pumpShiftRepository.saveAll(pumpShifts)).thenReturn(pumpShifts);
        transactionService.create(transactionDTOCreateList);
    }

    /**
     * param Hand over shift is not exist && accountsPayable > 0
     */
    @Test
    void create_UTCID02() throws CustomNotFoundException, CustomInternalServerException {
        //given
        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        User customer = User.builder()
                .id(1)
                .name("customer").build();
        UserDTO customerDTO = UserDTO.builder()
                .id(1)
                .name("customer").build();
        Card card = Card.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .customer(customer)
                .availableBalance(100d)
                .accountsPayable(20d)
                .build();
        CardDTO cardDTO = CardDTO.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .customer(customerDTO)
                .build();
        Station station = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        Fuel fuel = Fuel.builder()
                .id(23)
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();
        StationDTO stationDTO = StationDTO.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        FuelDTO fuelDTO = FuelDTO.builder()
                .id(23)
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();
        Tank tank = Tank.builder()
                .id(100)
                .name("tank_100")
                .volume(900d)
                .remain(20d)
                .currentPrice(505500d)
                .station(station)
                .fuel(fuel).build();
        TankDTO tankDTO = TankDTO.builder()
                .id(100)
                .name("tank_100")
                .volume(900d)
                .remain(20d)
                .currentPrice(505500d)
                .station(stationDTO)
                .fuel(fuelDTO).build();
        Pump pump = Pump.builder()
                .id(333)
                .name("Pump_333")
                .tank(tank)
                .note("pump333").build();
        PumpDTO pumpDTO = PumpDTO.builder()
                .id(333)
                .name("Pump_333")
                .tank(tankDTO)
                .note("pump333").build();
        PumpShift pumpShift = PumpShift.builder()
                .id(1)
                .shift(Shift.builder()
                        .id(1)
                        .name("shift")
                        .build())
                .pump(pump)
                .build();
        Transaction mockRepository = Transaction.builder()
                .id(1)
                .time(time)
                .volume(123d)
                .uuid("123456")
                .card(card)
                .pumpShift(pumpShift)
                .unitPrice(50500d).build();

        TransactionDTOCreate transactionDTOCreate = TransactionDTOCreate.builder()
                .time(time)
                .pumpId(1)
                .volume(123d)
                .uuid("123456")
                .cardId(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .unitPrice(50500d).build();

        TransactionDTO mockResult = TransactionDTO.builder()
                .id(123456)
                .time(time)
                .volume(123d)
                .uuid("123456")
                .card(cardDTO)
                .unitPrice(50500d).build();
        List<TransactionDTOCreate> transactionDTOCreateList = new ArrayList<TransactionDTOCreate>();
        LocalDateTime localDateTime = DateTimeHelper.toDateTime(transactionDTOCreate.getTime());
        LocalDate localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
        transactionDTOCreateList.add(transactionDTOCreate);
        long milliSeconds = (localDateTime.getHour() * 3600 + localDateTime.getMinute() * 60 + localDateTime.getSecond()) * 1000;

        Debt debt = Debt.builder().accountsPayable(100d).transaction(mockRepository).build();
        Mockito.when(debtRepository.save(Mockito.any(Debt.class))).thenReturn(debt);
        Mockito.when(handOverShiftCriteria.getPumpShiftToday()).thenReturn(pumpShift);
        Mockito.when(cardRepository.save(Mockito.any(Card.class))).thenReturn(card);
        Mockito.when(optionalValidate.getCardById(UUID.
                fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(card);
        Mockito.when(optionalValidate.getPumpShiftByPumpIdNotClose
                (100, LocalDateTime.of(localDate, LocalTime.MIN)
                        .atZone(TimeZone.getDefault()
                                .toZoneId()).toEpochSecond() * 1000, milliSeconds)).thenReturn(pumpShift);

        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(mockRepository);
        assertThrows(CustomNotFoundException.class, () -> {
            transactionService.create(transactionDTOCreateList);
        });
    }


    /**
     * param Hand over shift is exist && accountsPayable <= 0
     */
    @Test
    void create_UTCID03() throws CustomNotFoundException, CustomInternalServerException {
        //given
        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        User customer = User.builder()
                .id(1)
                .name("customer").build();
        UserDTO customerDTO = UserDTO.builder()
                .id(1)
                .name("customer").build();
        Card card = Card.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .customer(customer)
                .availableBalance(10000d)
                .accountsPayable(20d)
                .build();
        CardDTO cardDTO = CardDTO.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .customer(customerDTO)
                .build();
        Station station = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        Fuel fuel = Fuel.builder()
                .id(23)
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();
        StationDTO stationDTO = StationDTO.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        FuelDTO fuelDTO = FuelDTO.builder()
                .id(23)
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();
        Tank tank = Tank.builder()
                .id(100)
                .name("tank_100")
                .volume(900d)
                .remain(20d)
                .currentPrice(505500d)
                .station(station)
                .fuel(fuel).build();
        TankDTO tankDTO = TankDTO.builder()
                .id(100)
                .name("tank_100")
                .volume(900d)
                .remain(20d)
                .currentPrice(505500d)
                .station(stationDTO)
                .fuel(fuelDTO).build();
        Pump pump = Pump.builder()
                .id(333)
                .name("Pump_333")
                .tank(tank)
                .note("pump333").build();
        PumpDTO pumpDTO = PumpDTO.builder()
                .id(333)
                .name("Pump_333")
                .tank(tankDTO)
                .note("pump333").build();
        PumpShift pumpShift = PumpShift.builder()
                .id(1)
                .shift(Shift.builder()
                        .id(1)
                        .name("shift")
                        .build())
                .pump(pump)
                .build();
        PumpShift pumpShiftToday = PumpShift.builder()
                .id(100)
                .shift(Shift.builder()
                        .id(1)
                        .name("shift")
                        .build())
                .pump(pump)
                .build();
        Transaction mockRepository = Transaction.builder()
                .id(1)
                .time(time)
                .volume(123d)
                .uuid("123456")
                .card(card)
                .pumpShift(pumpShift)
                .unitPrice(50500d).build();

        TransactionDTOCreate transactionDTOCreate = TransactionDTOCreate.builder()
                .time(time)
                .pumpId(1)
                .volume(123d)
                .uuid("123456")
                .cardId(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .unitPrice(50500d).build();

        TransactionDTO mockResult = TransactionDTO.builder()
                .id(123456)
                .time(time)
                .volume(123d)
                .uuid("123456")
                .card(cardDTO)
                .unitPrice(50500d).build();
        List<TransactionDTOCreate> transactionDTOCreateList = new ArrayList<TransactionDTOCreate>();
        LocalDateTime localDateTime = DateTimeHelper.toDateTime(transactionDTOCreate.getTime());
        LocalDate localDate = LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
        transactionDTOCreateList.add(transactionDTOCreate);
        long milliSeconds = (localDateTime.getHour() * 3600 + localDateTime.getMinute() * 60 + localDateTime.getSecond()) * 1000;

        Debt debt = Debt.builder().accountsPayable(100d).transaction(mockRepository).build();
        Mockito.when(debtRepository.save(Mockito.any(Debt.class))).thenReturn(debt);
        Mockito.when(handOverShiftCriteria.getPumpShiftToday()).thenReturn(null);
        Mockito.when(cardRepository.save(Mockito.any(Card.class))).thenReturn(card);
        Mockito.when(optionalValidate.getCardById(UUID.
                fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(card);
        Mockito.when(optionalValidate.getPumpShiftByPumpIdNotClose
                (100, LocalDateTime.of(localDate, LocalTime.MIN)
                        .atZone(TimeZone.getDefault()
                                .toZoneId()).toEpochSecond() * 1000, milliSeconds)).thenReturn(pumpShiftToday);
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(mockRepository);
        List<Shift> shiftList = new ArrayList<>();
        shiftList.add(Shift.builder().id(1).build());
        Mockito.when(shiftRepository.findAllShiftByStationId(1)).thenReturn(shiftList);
        List<Pump> pumpList = new ArrayList<>();
        pumpList.add(pump);
        Mockito.when(pumpRepository.findAll()).thenReturn(pumpList);
        ArrayList<PumpShift> pumpShifts = new ArrayList<>();
        pumpShifts.add(pumpShiftToday);
        Mockito.when(pumpShiftRepository.saveAll(pumpShifts)).thenReturn(pumpShifts);
        transactionService.create(transactionDTOCreateList);
    }
}