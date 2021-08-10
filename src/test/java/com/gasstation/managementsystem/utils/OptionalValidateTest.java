//package com.gasstation.managementsystem.utils;
//
//import com.gasstation.managementsystem.entity.*;
//import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
//import com.gasstation.managementsystem.model.dto.user.UserDTO;
//import com.gasstation.managementsystem.repository.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//class OptionalValidateTest {
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private UserTypeRepository userTypeRepository;
//    @Mock
//    private TankRepository tankRepository;
//    @Mock
//    private StationRepository stationRepository;
//    @Mock
//    private FuelRepository fuelRepository;
//    @Mock
//    private PumpRepository pumpRepository;
//    @Mock
//    private TransactionRepository transactionRepository;
//    @Mock
//    private ShiftRepository shiftRepository;
//    @Mock
//    private CardRepository cardRepository;
//    @Mock
//    private ApiRepository apiRepository;
//    @Mock
//    private SupplierRepository supplierRepository;
//    @Mock
//    private EmployeeRepository employeeRepository;
//    @Mock
//    private WorkScheduleRepository workScheduleRepository;
//    @Mock
//    private FuelImportRepository fuelImportRepository;
//    @Mock
//    private ExpenseRepository expenseRepository;
//    @Mock
//    private PumpShiftRepository pumpShiftRepository;
//    @Mock
//    private ReceiptRepository receiptRepository;
//    @InjectMocks
//    private OptionalValidate optionalValidate;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    /**
//     * userOptional is not Empty
//     */
//    @Test
//    void getUserById_UTCID01() throws CustomNotFoundException {
//        //given
//        Long dateOfBirth = 16094340000000L;
//        User mockResult = User.builder()
//                .username("user")
//                .identityCardNumber("0136235943")
//                .password("123456")
//                .name("Le Viet Tu")
//                .gender(false)
//                .dateOfBirth(dateOfBirth)
//                .address("Me Linh")
//                .phone("0886818939")
//                .email("motdemhuyhoang@gmail.com")
//                .note("")
//                .active(false)
//                .userType(UserType.builder().id(1).type("ADMIN").build())
//                .build();
//        Optional<User> userOptional = Optional.of(mockResult);
//        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(userOptional);
//        assertEquals(mockResult, optionalValidate.getUserById(1));
//    }
//
//    /**
//     * userOptional is Empty
//     */
//    @Test
//    void getUserById_UTCID02() {
//        //given
//        Long dateOfBirth = 16094340000000L;
//        User mockResult = User.builder()
//                .id(1)
//                .username("user")
//                .identityCardNumber("0136235943")
//                .password("123456")
//                .name("Le Viet Tu")
//                .gender(false)
//                .dateOfBirth(dateOfBirth)
//                .address("Me Linh")
//                .phone("0886818939")
//                .email("motdemhuyhoang@gmail.com")
//                .note("")
//                .active(false)
//                .userType(UserType.builder().id(1).type("ADMIN").build())
//                .build();
//        Optional<User> userOptional = Optional.of(mockResult);
//        Mockito.when(userRepository.findById(1)).thenReturn(userOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            optionalValidate.getUserById(2);
//        });
//    }
//
//    /**
//     * userTypeOptional is not Empty
//     */
//    @Test
//    void getUserTypeById_UTCID01() throws CustomNotFoundException {
//        UserType mockResult = UserType.builder().id(1).type("ADMIN").build();
//        Optional<UserType> userTypeOptional = Optional.of(mockResult);
//        Mockito.when(userTypeRepository.findById(1)).thenReturn(userTypeOptional);
//        assertEquals(mockResult, optionalValidate.getUserTypeById(1));
//    }
//
//    /**
//     * userTypeOptional is Empty
//     */
//    @Test
//    void getUserTypeById_UTCID02() {
//        UserType mockResult = UserType.builder().id(1).type("ADMIN").build();
//        Optional<UserType> userTypeOptional = Optional.of(mockResult);
//        Mockito.when(userTypeRepository.findById(1)).thenReturn(userTypeOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            optionalValidate.getUserTypeById(2);
//        });
//    }
//
//    /**
//     * tankOptional is not Empty
//     */
//    @Test
//    void getTankById_UTCID01() throws CustomNotFoundException {
//        Station station = Station.builder().id(2).build();
//        Tank mockResult = Tank.builder().name("tank1")
//                .id(1)
//                .station(station)
//                .volume(333d)
//                .currentPrice(444d)
//                .remain(0d)
//                .build();
//        Optional<Tank> tankOptional = Optional.of(mockResult);
//        Mockito.when(tankRepository.findById(1)).thenReturn(tankOptional);
//        assertEquals(mockResult, optionalValidate.getTankById(1));
//    }
//
//    /**
//     * tankOptional is Empty
//     */
//    @Test
//    void getTankById_UTCID02() {
//        Station station = Station.builder().id(2).build();
//        Tank mockResult = Tank.builder().name("tank1")
//                .id(1)
//                .station(station)
//                .volume(333d)
//                .currentPrice(444d)
//                .remain(0d)
//                .build();
//        Optional<Tank> tankOptional = Optional.of(mockResult);
//        Mockito.when(tankRepository.findById(1)).thenReturn(tankOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            optionalValidate.getTankById(2);
//        });
//    }
//
//    /**
//     * tankOptional is not Empty
//     */
//    @Test
//    void getStationById_UTCID01() throws CustomNotFoundException {
//        Station mockResult = Station.builder()
//                .id(1).name("Hoang Long")
//                .address("Me Linh- Ha Noi")
//                .longitude(39d)
//                .latitude(46d)
//                .build();
//        Optional<Station> stationOptional = Optional.of(mockResult);
//        Mockito.when(stationRepository.findById(1)).thenReturn(stationOptional);
//        assertEquals(mockResult, optionalValidate.getStationById(1));
//    }
//
//    /**
//     * tankOptional is Empty
//     */
//    @Test
//    void getStationById_UTCID02() {
//        Station mockResult = Station.builder()
//                .id(1).name("Hoang Long")
//                .address("Me Linh- Ha Noi")
//                .longitude(39d)
//                .latitude(46d)
//                .build();
//        Optional<Station> stationOptional = Optional.of(mockResult);
//        Mockito.when(stationRepository.findById(1)).thenReturn(stationOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            optionalValidate.getStationById(2);
//        });
//    }
//
//    /**
//     * fuelOptional is not Empty
//     */
//    @Test
//    void getFuelById_UTCID01() throws CustomNotFoundException {
//        Fuel mockResult = Fuel.builder().id(1).name("RON95").unit("litter").type("Xăng").price(21000d).build();
//        Optional<Fuel> fuelOptional = Optional.of(mockResult);
//        Mockito.when(fuelRepository.findById(1)).thenReturn(fuelOptional);
//        assertEquals(mockResult, optionalValidate.getFuelById(1));
//    }
//
//    /**
//     * fuelOptional is Empty
//     */
//    @Test
//    void getFuelById_UTCID02() {
//        Fuel mockResult = Fuel.builder().id(1).name("RON95").unit("litter").type("Xăng").price(21000d).build();
//        Optional<Fuel> fuelOptional = Optional.of(mockResult);
//        Mockito.when(fuelRepository.findById(1)).thenReturn(fuelOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getFuelById(2));
//        });
//    }
//
//    /**
//     * pumpOptional is not Empty
//     */
//    @Test
//    void getPumpById_UTCID01() throws CustomNotFoundException {
//        User user = User.builder().id(1).build();
//        Station station = Station.builder().id(1).owner(user).build();
//        Tank tank = Tank.builder().name("tank1")
//                .id(1)
//                .station(station)
//                .build();
//        Pump mockResult = Pump.builder()
//                .id(1)
//                .tank(tank)
//                .name("pump1")
//                .note("pump1").build();
//        Optional<Pump> pumpOptional = Optional.of(mockResult);
//        Mockito.when(pumpRepository.findById(1)).thenReturn(pumpOptional);
//        assertEquals(mockResult, optionalValidate.getPumpById(1));
//    }
//
//    /**
//     * pumpOptional is Empty
//     */
//    @Test
//    void getPumpById_UTCID02() {
//        User user = User.builder().id(1).build();
//        Station station = Station.builder().id(1).owner(user).build();
//        Tank tank = Tank.builder().name("tank1")
//                .id(1)
//                .station(station)
//                .build();
//        Pump mockResult = Pump.builder()
//                .id(1)
//                .tank(tank)
//                .name("pump1")
//                .note("pump1").build();
//        Optional<Pump> pumpOptional = Optional.of(mockResult);
//        Mockito.when(pumpRepository.findById(1)).thenReturn(pumpOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getPumpById(2));
//        });
//    }
//
//    /**
//     * pumpCodeOptional is not Empty
//     */
//    @Test
//    void getTransactionById_UTCID01() throws CustomNotFoundException {
//        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
//        User customer = User.builder()
//                .id(1)
//                .name("customer").build();
//        UserDTO customerDTO = UserDTO.builder()
//                .id(1)
//                .name("customer").build();
//        Card card = Card.builder()
//                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .customer(customer)
//                .availableBalance(100d)
//                .accountsPayable(20d)
//                .build();
//        Station station = Station.builder()
//                .id(1)
//                .name("Hoang Long")
//                .address("Me Linh- Ha Noi")
//                .longitude(39d)
//                .latitude(46d)
//                .build();
//        Fuel fuel = Fuel.builder()
//                .id(23)
//                .name("E92")
//                .unit("litter")
//                .price(32460d)
//                .type("TYPE_E92").build();
//        Tank tank = Tank.builder()
//                .id(100)
//                .name("tank_100")
//                .volume(900d)
//                .remain(20d)
//                .currentPrice(505500d)
//                .station(station)
//                .fuel(fuel).build();
//
//        Pump pump = Pump.builder()
//                .id(333)
//                .name("Pump_333")
//                .tank(tank)
//                .note("pump333").build();
//        PumpShift pumpShift = PumpShift.builder()
//                .id(1)
//                .shift(Shift.builder()
//                        .id(1)
//                        .name("shift")
//                        .build())
//                .pump(pump)
//                .build();
//        Transaction mockResult = Transaction.builder()
//                .id(1)
//                .time(time)
//                .volume(123d)
//                .uuid("123456")
//                .card(card)
//                .pumpShift(pumpShift)
//                .unitPrice(50500d).build();
//        Optional<Transaction> transactionOptional = Optional.of(mockResult);
//        Mockito.when(transactionRepository.findById(1)).thenReturn(transactionOptional);
//        assertEquals(mockResult, optionalValidate.getTransactionById(1));
//    }
//
//    /**
//     * pumpOptional is Empty
//     */
//    @Test
//    void getTransactionById_UTCID02() throws CustomNotFoundException {
//        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
//        User customer = User.builder()
//                .id(1)
//                .name("customer").build();
//        UserDTO customerDTO = UserDTO.builder()
//                .id(1)
//                .name("customer").build();
//        Card card = Card.builder()
//                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .customer(customer)
//                .availableBalance(100d)
//                .accountsPayable(20d)
//                .build();
//        Station station = Station.builder()
//                .id(1)
//                .name("Hoang Long")
//                .address("Me Linh- Ha Noi")
//                .longitude(39d)
//                .latitude(46d)
//                .build();
//        Fuel fuel = Fuel.builder()
//                .id(23)
//                .name("E92")
//                .unit("litter")
//                .price(32460d)
//                .type("TYPE_E92").build();
//        Tank tank = Tank.builder()
//                .id(100)
//                .name("tank_100")
//                .volume(900d)
//                .remain(20d)
//                .currentPrice(505500d)
//                .station(station)
//                .fuel(fuel).build();
//
//        Pump pump = Pump.builder()
//                .id(333)
//                .name("Pump_333")
//                .tank(tank)
//                .note("pump333").build();
//        PumpShift pumpShift = PumpShift.builder()
//                .id(1)
//                .shift(Shift.builder()
//                        .id(1)
//                        .name("shift")
//                        .build())
//                .pump(pump)
//                .build();
//        Transaction mockResult = Transaction.builder()
//                .id(1)
//                .time(time)
//                .volume(123d)
//                .uuid("123456")
//                .card(card)
//                .pumpShift(pumpShift)
//                .unitPrice(50500d).build();
//        Optional<Transaction> transactionOptional = Optional.of(mockResult);
//        Mockito.when(transactionRepository.findById(1)).thenReturn(transactionOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getTransactionById(2));
//        });
//    }
//
//    /**
//     * shiftOptional is not Empty
//     */
//    @Test
//    void getShiftById_UTCID01() throws CustomNotFoundException {
//        //given
//        Long startTime = 25200000L;
//        Long endTime = 43200000L;
//        Shift mockResult = Shift.builder()
//                .id(1)
//                .name("Morning")
//                .startTime(startTime)
//                .endTime(endTime)
//                .station(Station.builder()
//                        .id(1)
//                        .name("station")
//                        .address("address")
//                        .owner(User.builder()
//                                .id(1)
//                                .name("owner")
//                                .build())
//                        .build())
//                .build();
//        Optional<Shift> shiftOptional = Optional.of(mockResult);
//        Mockito.when(shiftRepository.findById(1)).thenReturn(shiftOptional);
//        assertEquals(mockResult, optionalValidate.getShiftById(1));
//    }
//
//    /**
//     * shiftOptional is Empty
//     */
//    @Test
//    void getShiftById_UTCID02() {
//        //given
//        Long startTime = 25200000L;
//        Long endTime = 43200000L;
//        Shift mockResult = Shift.builder()
//                .id(1)
//                .name("Morning")
//                .startTime(startTime)
//                .endTime(endTime)
//                .station(Station.builder()
//                        .id(1)
//                        .name("station")
//                        .address("address")
//                        .owner(User.builder()
//                                .id(1)
//                                .name("owner")
//                                .build())
//                        .build())
//                .build();
//        Optional<Shift> shiftOptional = Optional.of(mockResult);
//        Mockito.when(shiftRepository.findById(1)).thenReturn(shiftOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getShiftById(2));
//        });
//    }
//
//    /**
//     * shiftOptional is not Empty
//     */
//    @Test
//    void getCardById_UTCID01() throws CustomNotFoundException {
//        Long limitSetDate = 16094340000000L;
//        Long issuedDate = 16094540000000L;
//        Long activeDate = 16094340000000L;
//        Card mockResult = Card.builder()
//                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .driverPhone("0123456789")
//                .driverName("Nguyen Van A")
//                .licensePlate("36B1-75097")
//                .initialDebt(100d)
//                .availableBalance(1000d)
//                .accountsPayable(200d)
//                .debtLimit(5000d)
//                .limitSetDate(limitSetDate)
//                .createdDate(issuedDate)
//                .active(true)
//                .creator(User.builder().id(1).name("admin").build())
//                .customer(User.builder().id(2).name("customer").build())
//                .build();
//        Optional<Card> shiftOptional = Optional.of(mockResult);
//        Mockito.when(cardRepository.findById(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(shiftOptional);
//        assertEquals(mockResult, optionalValidate.getCardById(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")));
//    }
//
//    /**
//     * shiftOptional is Empty
//     */
//    @Test
//    void getCardById_UTCID02() {
//        Long limitSetDate = 16094340000000L;
//        Long issuedDate = 16094540000000L;
//        Long activeDate = 16094340000000L;
//        Card mockResult = Card.builder()
//                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .driverPhone("0123456789")
//                .driverName("Nguyen Van A")
//                .licensePlate("36B1-75097")
//                .initialDebt(100d)
//                .availableBalance(1000d)
//                .accountsPayable(200d)
//                .debtLimit(5000d)
//                .limitSetDate(limitSetDate)
//                .createdDate(issuedDate)
//                .active(true)
//                .creator(User.builder().id(1).name("admin").build())
//                .customer(User.builder().id(2).name("customer").build())
//                .build();
//        Optional<Card> shiftOptional = Optional.of(mockResult);
//        Mockito.when(cardRepository.findById(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(shiftOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getCardById(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c9")));
//        });
//    }
//
//    /**
//     * apiOptional is not Empty
//     */
//    @Test
//    void getApiById_UTCID01() throws CustomNotFoundException {
//        Set<UserType> userTypes = new HashSet<>();
//        userTypes.add(UserType.builder().id(1).type("ADMIN").build());
//        Api mockResult = Api.builder()
//                .id(1)
//                .method("GET")
//                .path("/api/v1/users")
//                .userTypeList(userTypes).build();
//        Optional<Api> apiOptional = Optional.of(mockResult);
//        Mockito.when(apiRepository.findById(1)).thenReturn(apiOptional);
//        assertEquals(mockResult, optionalValidate.getApiById(1));
//    }
//
//    /**
//     * apiOptional is Empty
//     */
//    @Test
//    void getApiById_UTCID02() {
//        Set<UserType> userTypes = new HashSet<>();
//        userTypes.add(UserType.builder().id(1).type("ADMIN").build());
//        Api mockResult = Api.builder()
//                .id(1)
//                .method("GET")
//                .path("/api/v1/users")
//                .userTypeList(userTypes).build();
//        Optional<Api> apiOptional = Optional.of(mockResult);
//        Mockito.when(apiRepository.findById(1)).thenReturn(apiOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getApiById(2));
//        });
//    }
//
//    /**
//     * supplierOptional is not Empty
//     */
//    @Test
//    void getSupplierById_UTCID01() throws CustomNotFoundException {
//        Supplier mockResult = Supplier.builder()
//                .id(1)
//                .name("Khanh Huyen")
//                .phone("0989656431")
//                .address("Thach That")
//                .note("Chu tich")
//                .build();
//        Optional<Supplier> supplierOptional = Optional.of(mockResult);
//        Mockito.when(supplierRepository.findById(1)).thenReturn(supplierOptional);
//        assertEquals(mockResult, optionalValidate.getSupplierById(1));
//    }
//
//    /**
//     * supplierOptional is Empty
//     */
//    @Test
//    void getSupplierById_UTCID02() {
//        Supplier mockResult = Supplier.builder()
//                .id(1)
//                .name("Khanh Huyen")
//                .phone("0989656431")
//                .address("Thach That")
//                .note("Chu tich")
//                .build();
//        Optional<Supplier> supplierOptional = Optional.of(mockResult);
//        Mockito.when(supplierRepository.findById(1)).thenReturn(supplierOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getSupplierById(2));
//        });
//    }
//
//    /**
//     * employeeOptional is not Empty
//     */
//    @Test
//    void getEmployeeById_UTCID01() throws CustomNotFoundException {
//        Long dateOfBirth = 16094340000000L;
//        //given
//        Station station = Station.builder().id(1).name("station").build();
//        Employee mockResult = Employee.builder()
//                .id(1)
//                .name("employee")
//                .address("Ha Noi")
//                .phone("0123456789")
//                .gender(true)
//                .dateOfBirth(dateOfBirth)
//                .identityCardNumber("12345678900")
//                .station(station)
//                .build();
//        Optional<Employee> employeeOptional = Optional.of(mockResult);
//        Mockito.when(employeeRepository.findById(1)).thenReturn(employeeOptional);
//        assertEquals(mockResult, optionalValidate.getEmployeeById(1));
//    }
//
//    /**
//     * employeeOptional is Empty
//     */
//    @Test
//    void getEmployeeById_UTCID02() {
//        Long dateOfBirth = 16094340000000L;
//        //given
//        Station station = Station.builder().id(1).name("station").build();
//        Employee mockResult = Employee.builder()
//                .id(1)
//                .name("employee")
//                .address("Ha Noi")
//                .phone("0123456789")
//                .gender(true)
//                .dateOfBirth(dateOfBirth)
//                .identityCardNumber("12345678900")
//                .station(station)
//                .build();
//        Optional<Employee> employeeOptional = Optional.of(mockResult);
//        Mockito.when(employeeRepository.findById(1)).thenReturn(employeeOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getEmployeeById(2));
//        });
//    }
//
//    /**
//     * workScheduleOptional is not Empty
//     */
//    @Test
//    void getWorkScheduleById_UTCID01() throws CustomNotFoundException {
//        //given
//        User owner = User.builder().id(1).name("owner").build();
//        Station station = Station.builder()
//                .id(1)
//                .name("station")
//                .address("address")
//                .owner(owner).build();
//        Employee employee = Employee.builder()
//                .id(1)
//                .name("employee")
//                .station(station)
//                .build();
//        Long startTime = 25200000L;
//        Long endTime = 43200000L;
//        Shift shift = Shift.builder()
//                .id(1)
//                .name("shift")
//                .startTime(startTime)
//                .endTime(endTime)
//                .build();
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        WorkSchedule mockResult = WorkSchedule.builder()
//                .id(1)
//                .employee(employee)
//                .shift(shift)
//                .startDate(startDate)
//                .endDate(endDate).build();
//        Optional<WorkSchedule> workScheduleOptional = Optional.of(mockResult);
//        Mockito.when(workScheduleRepository.findById(1)).thenReturn(workScheduleOptional);
//        assertEquals(mockResult, optionalValidate.getWorkScheduleById(1));
//    }
//
//    /**
//     * workScheduleOptional is Empty
//     */
//    @Test
//    void getWorkScheduleById_UTCID02() {
//        //given
//        User owner = User.builder().id(1).name("owner").build();
//        Station station = Station.builder()
//                .id(1)
//                .name("station")
//                .address("address")
//                .owner(owner).build();
//        Employee employee = Employee.builder()
//                .id(1)
//                .name("employee")
//                .station(station)
//                .build();
//        Long startTime = 25200000L;
//        Long endTime = 43200000L;
//        Shift shift = Shift.builder()
//                .id(1)
//                .name("shift")
//                .startTime(startTime)
//                .endTime(endTime)
//                .build();
//        Long startDate = 16094340000000L;
//        Long endDate = 16094440000000L;
//        WorkSchedule mockResult = WorkSchedule.builder()
//                .id(1)
//                .employee(employee)
//                .shift(shift)
//                .startDate(startDate)
//                .endDate(endDate).build();
//        Optional<WorkSchedule> workScheduleOptional = Optional.of(mockResult);
//        Mockito.when(workScheduleRepository.findById(1)).thenReturn(workScheduleOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getWorkScheduleById(2));
//        });
//    }
//
//    /**
//     * fuelImportOptional is not Empty
//     */
//    @Test
//    void getFuelImportById_UTCID01() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        FuelImport mockResult = FuelImport.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10)
//                .unitPrice(100)
//                .amountPaid(50d)
//                .vatPercent(0)
//                .note("note")
//                .tank(Tank.builder().id(1).name("tank").build())
//                .supplier(Supplier.builder().id(1).name("supplier").build())
//                .build();
//        Optional<FuelImport> fuelImportOptional = Optional.of(mockResult);
//        Mockito.when(fuelImportRepository.findById(1)).thenReturn(fuelImportOptional);
//        assertEquals(mockResult, optionalValidate.getFuelImportById(1));
//    }
//
//    /**
//     * fuelImportOptional is Empty
//     */
//    @Test
//    void getFuelImportById_UTCID02() {
//        Long creatDate = 16094340000000L;
//        FuelImport mockResult = FuelImport.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10)
//                .unitPrice(100)
//                .amountPaid(50d)
//                .vatPercent(0)
//                .note("note")
//                .tank(Tank.builder().id(1).name("tank").build())
//                .supplier(Supplier.builder().id(1).name("supplier").build())
//                .build();
//        Optional<FuelImport> fuelImportOptional = Optional.of(mockResult);
//        Mockito.when(fuelImportRepository.findById(1)).thenReturn(fuelImportOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getFuelImportById(2));
//        });
//    }
//
//    /**
//     * expenseOptional is not Empty
//     */
//    @Test
//    void getExpenseById_UTCID01() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        Expense mockResult = Expense.builder()
//                .id(1)
//                .reason("reason")
//                .amount(100d)
//                .createdDate(creatDate)
//                .station(Station.builder()
//                        .id(1)
//                        .name("station")
//                        .build())
//                .fuelImport(FuelImport.builder()
//                        .id(1)
//                        .name("fuelImport")
//                        .build())
//                .build();
//        Optional<Expense> expenseOptional = Optional.of(mockResult);
//        Mockito.when(expenseRepository.findById(1)).thenReturn(expenseOptional);
//        assertEquals(mockResult, optionalValidate.getExpenseById(1));
//    }
//
//    /**
//     * expenseOptional is Empty
//     */
//    @Test
//    void getExpenseById_UTCID02() {
//        Long creatDate = 16094340000000L;
//        Expense mockResult = Expense.builder()
//                .id(1)
//                .reason("reason")
//                .amount(100d)
//                .createdDate(creatDate)
//                .station(Station.builder()
//                        .id(1)
//                        .name("station")
//                        .build())
//                .fuelImport(FuelImport.builder()
//                        .id(1)
//                        .name("fuelImport")
//                        .build())
//                .build();
//        Optional<Expense> expenseOptional = Optional.of(mockResult);
//        Mockito.when(expenseRepository.findById(1)).thenReturn(expenseOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getExpenseById(2));
//        });
//    }
//
//    /**
//     * handOverShiftOptional is not Empty
//     */
//    @Test
//    void getHandOverShiftById_UTCID01() throws CustomNotFoundException {
//        Long createDate = 16094340000000L;
//        Long closedTime = 16094440000000L;
//        PumpShift mockResult = PumpShift.builder()
//                .id(1)
//                .shift(Shift.builder().id(1).station(Station.builder().id(1).build()).build())
//                .pump(Pump.builder().id(1).build())
//                .executor((User.builder().id(3).name("EMPLOYEE").build()))
//                .closedTime(closedTime)
//                .createdDate(createDate).build();
//        Optional<PumpShift> handOverShiftOptional = Optional.of(mockResult);
//        Mockito.when(pumpShiftRepository.findById(1)).thenReturn(handOverShiftOptional);
//        assertEquals(mockResult, optionalValidate.getPumpShiftById(1));
//    }
//
//    /**
//     * handOverShiftOptional is Empty
//     */
//    @Test
//    void getHandOverShiftById_UTCID02() {
//        Long createDate = 16094340000000L;
//        Long closedTime = 16094440000000L;
//        PumpShift mockResult = PumpShift.builder()
//                .id(1)
//                .shift(Shift.builder().id(1).station(Station.builder().id(1).build()).build())
//                .pump(Pump.builder().id(1).build())
//                .executor((User.builder().id(3).name("EMPLOYEE").build()))
//                .closedTime(closedTime)
//                .createdDate(createDate).build();
//        Optional<PumpShift> handOverShiftOptional = Optional.of(mockResult);
//        Mockito.when(pumpShiftRepository.findById(1)).thenReturn(handOverShiftOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getPumpShiftById(2));
//        });
//    }
//
//    @Test
//    void getHandOverShiftByPumpIdNotClose() {
//        Long createDate = 16094340000000L;
//        Long closedTime = 16094440000000L;
//        PumpShift mockResult = PumpShift.builder()
//                .id(1)
//                .shift(Shift.builder().id(1).station(Station.builder().id(1).build()).build())
//                .pump(Pump.builder().id(1).build())
//                .executor((User.builder().id(3).name("EMPLOYEE").build()))
//                .closedTime(closedTime)
//                .createdDate(createDate).build();
//        Optional<PumpShift> handOverShiftOptional = Optional.of(mockResult);
//        Mockito.when(pumpShiftRepository.findByPumpIdNotClose(1, createDate, closedTime)).thenReturn(handOverShiftOptional);
//        assertEquals(mockResult, optionalValidate.getPumpShiftByPumpIdNotClose(1, createDate, closedTime));
//    }
//
//    /**
//     * receiptOptional is not Empty
//     */
//    @Test
//    void getReceiptById_UTCID01() throws CustomNotFoundException {
//        Long createdDate = 16094340000000L;
//        Card card = Card.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build();
//        Debt debt = Debt.builder().id(1).accountsPayable(1000d).build();
//        User user = User.builder().id(1).build();
//        Receipt mockResult = Receipt.builder().id(1)
//                .card(Card.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                .createdDate(createdDate)
//                .amount(100d)
//                .creator(User.builder().id(1).build())
//                .reason("reason")
//                .debt(debt)
//                .build();
//        Optional<Receipt> receiptOptional = Optional.of(mockResult);
//        Mockito.when(receiptRepository.findById(1)).thenReturn(receiptOptional);
//        assertEquals(mockResult, optionalValidate.getReceiptById(1));
//    }
//
//    /**
//     * receiptOptional is Empty
//     */
//    @Test
//    void getReceiptById_UTCID02() {
//        Long createdDate = 16094340000000L;
//        Card card = Card.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build();
//        Debt debt = Debt.builder().id(1).accountsPayable(1000d).build();
//        User user = User.builder().id(1).build();
//        Receipt mockResult = Receipt.builder().id(1)
//                .card(Card.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                .createdDate(createdDate)
//                .amount(100d)
//                .creator(User.builder().id(1).build())
//                .reason("reason")
//                .debt(debt)
//                .build();
//        Optional<Receipt> receiptOptional = Optional.of(mockResult);
//        Mockito.when(receiptRepository.findById(1)).thenReturn(receiptOptional);
//        assertThrows(CustomNotFoundException.class, () -> {
//            assertEquals(mockResult, optionalValidate.getReceiptById(2));
//        });
//    }
//
//    /**
//     * receiptOptional is not Empty
//     */
//    @Test
//    void getDebtById_UTCID01() {
//
//    }
//
//    /**
//     * receiptOptional is Empty
//     */
//    @Test
//    void getDebtById_UTCID02() {
//
//    }
//
//}