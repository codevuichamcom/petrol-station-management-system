//package com.gasstation.managementsystem.service.impl;
//
//import com.gasstation.managementsystem.entity.*;
//import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
//import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
//import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
//import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOCreate;
//import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOUpdate;
//import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
//import com.gasstation.managementsystem.model.dto.tank.TankDTO;
//import com.gasstation.managementsystem.model.mapper.FuelImportMapper;
//import com.gasstation.managementsystem.repository.FuelImportRepository;
//import com.gasstation.managementsystem.utils.OptionalValidate;
//import com.gasstation.managementsystem.utils.UserHelper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class FuelImportServiceImplTest {
//    @Mock
//    private FuelImportRepository fuelImportRepository;
//
//    @Mock
//    private OptionalValidate optionalValidate;
//    @Mock
//    private UserHelper userHelper;
//
//    @InjectMocks
//    private FuelImportServiceImpl fuelImportService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    /**
//     * param sort
//     */
//    @Test
//    void findAll_UTCID01() {
//        List<FuelImport> mockRepository = new ArrayList<>();
//        List<FuelImportDTO> mockResult = new ArrayList<>();
//        mockData(mockRepository, mockResult);
//
//        Mockito.when(fuelImportRepository.findAllFuelImport(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(mockRepository);
//        List<FuelImportDTO> listResultService = (List<FuelImportDTO>) fuelImportService.findAll().get("data");
//        for (int i = 0; i < listResultService.size(); i++) {
//            FuelImportDTO o1 = mockResult.get(i);
//            FuelImportDTO o2 = listResultService.get(i);
//            assertEquals(o1, o2);
//        }
//    }
//
//    /**
//     * param pageable
//     */
//    @Test
//    void findAll_UTCID02() {
//        List<FuelImport> mockRepository = new ArrayList<>();
//        List<FuelImportDTO> mockResult = new ArrayList<>();
//        mockData(mockRepository, mockResult);
//
//        final int PAGE_INDEX = 1;
//        final int PAGE_SIZE = 3;
//        Page<FuelImport> mockRepositoryPaged = new PageImpl<>(mockRepository.subList(PAGE_INDEX - 1, PAGE_SIZE));
//        List<FuelImportDTO> mockResultPaged = mockResult.subList(PAGE_INDEX - 1, PAGE_SIZE);
//        Mockito.when(fuelImportRepository.findAll(PageRequest.of(PAGE_INDEX - 1, PAGE_SIZE))).thenReturn(mockRepositoryPaged);
//        List<FuelImportDTO> listResultService = (List<FuelImportDTO>) fuelImportService.findAll(PageRequest.of(PAGE_INDEX - 1, PAGE_SIZE)).get("data");
//        for (int i = 0; i < listResultService.size(); i++) {
//            FuelImportDTO o1 = mockResultPaged.get(i);
//            FuelImportDTO o2 = listResultService.get(i);
//            assertEquals(o1, o2);
//        }
//    }
//
//    private void mockData(List<FuelImport> mockRepository, List<FuelImportDTO> mockResult) {
//        for (int i = 1; i <= 10; i++) {
//            Long creatDate = 16094340000000L;
//            FuelImport fuelImport = FuelImport.builder()
//                    .id(1)
//                    .name("name")
//                    .createdDate(creatDate)
//                    .volume(10)
//                    .unitPrice(100)
//                    .amountPaid(50d)
//                    .vatPercent(0)
//                    .note("note")
//                    .tank(Tank.builder().id(1).name("tank").build())
//                    .supplier(Supplier.builder().id(1).name("supplier").build())
//                    .build();
//            mockRepository.add(fuelImport);
//            mockResult.add(FuelImportMapper.toFuelImportDTO(fuelImport));
//        }
//    }
//
//    @Test
//    void findById() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        FuelImport mockRepository = FuelImport.builder()
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
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(TankDTO.builder().id(1).name("tank").build())
//                .supplier(SupplierDTO.builder().id(1).name("supplier").build())
//                .build();
//        Mockito.when(optionalValidate.getFuelImportById(1)).thenReturn(mockRepository);
//        assertEquals(mockResult, fuelImportService.findById(1));
//    }
//
//    @Test
//    void create() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        User user = User.builder().id(1).build();
//        Tank tank = Tank.builder().id(1).name("tank").build();
//        TankDTO tankDTO = TankDTO.builder().id(1).name("tank").build();
//        Supplier supplier = Supplier.builder().id(1).name("supplier").build();
//        SupplierDTO supplierDTO = SupplierDTO.builder().id(1).name("supplier").build();
//        Fuel fuel = Fuel.builder().id(1).build();
//        FuelDTO fuelDTO = FuelDTO.builder().id(1).build();
//        FuelImport mockRepository = FuelImport.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10)
//                .unitPrice(100)
//                .amountPaid(50d)
//                .vatPercent(0)
//                .note("note")
//                .tank(tank)
//                .supplier(supplier)
//                .fuel(fuel)
//                .build();
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(tankDTO)
//                .fuel(fuelDTO)
//                .supplier(supplierDTO)
//                .build();
//        FuelImportDTOCreate fuelImportDTOCreate = FuelImportDTOCreate.builder()
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .tankId(1)
//                .fuelId(1)
//                .supplierId(1)
//                .build();
//        Mockito.when(optionalValidate.getTankById(1)).thenReturn(tank);
//        Mockito.when(optionalValidate.getSupplierById(1)).thenReturn(supplier);
//        Mockito.when(optionalValidate.getFuelById(1)).thenReturn(fuel);
//        Mockito.when(userHelper.getUserLogin()).thenReturn(user);
//        Mockito.when(fuelImportRepository.save(Mockito.any(FuelImport.class))).thenReturn(mockRepository);
//        assertEquals(mockResult, fuelImportService.create(fuelImportDTOCreate));
//    }
//
//    /**
//     * param tankId,supplierId,fuelId is null
//     */
//    @Test
//    void update_UTCID01() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        Tank tank = Tank.builder().id(1).name("tank").build();
//        TankDTO tankDTO = TankDTO.builder().id(1).name("tank").build();
//        Supplier supplier = Supplier.builder().id(1).name("supplier").build();
//        SupplierDTO supplierDTO = SupplierDTO.builder().id(1).name("supplier").build();
//        Fuel fuel = Fuel.builder().id(1).build();
//        FuelDTO fuelDTO = FuelDTO.builder().id(1).build();
//        FuelImport mockRepository = FuelImport.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10)
//                .unitPrice(100)
//                .amountPaid(50d)
//                .vatPercent(0)
//                .note("note")
//                .tank(tank)
//                .supplier(supplier)
//                .fuel(fuel)
//                .build();
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name_update")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(tankDTO)
//                .fuel(fuelDTO)
//                .supplier(supplierDTO)
//                .build();
//        FuelImportDTOUpdate fuelImportDTOUpdate = FuelImportDTOUpdate.builder()
//                .name("name_update")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .build();
//        Mockito.when(optionalValidate.getFuelImportById(1)).thenReturn(mockRepository);
//        Mockito.when(optionalValidate.getTankById(1)).thenReturn(tank);
//        Mockito.when(optionalValidate.getSupplierById(1)).thenReturn(supplier);
//        Mockito.when(optionalValidate.getFuelById(1)).thenReturn(fuel);
//        Mockito.when(fuelImportRepository.save(Mockito.any(FuelImport.class))).thenReturn(mockRepository);
//        assertEquals(mockResult, fuelImportService.update(1, fuelImportDTOUpdate));
//    }
//
//    /**
//     * param tankId is null
//     */
//    @Test
//    void update_UTCID02() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        Tank tank = Tank.builder().id(1).name("tank").build();
//        TankDTO tankDTO = TankDTO.builder().id(1).name("tank").build();
//        Supplier supplier = Supplier.builder().id(1).name("supplier").build();
//        SupplierDTO supplierDTO = SupplierDTO.builder().id(1).name("supplier").build();
//        Fuel fuel = Fuel.builder().id(1).build();
//        FuelDTO fuelDTO = FuelDTO.builder().id(1).build();
//        FuelImport mockRepository = FuelImport.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10)
//                .unitPrice(100)
//                .amountPaid(50d)
//                .vatPercent(0)
//                .note("note")
//                .tank(tank)
//                .supplier(supplier)
//                .fuel(fuel)
//                .build();
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name_update")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(tankDTO)
//                .fuel(fuelDTO)
//                .supplier(supplierDTO)
//                .build();
//        FuelImportDTOUpdate fuelImportDTOUpdate = FuelImportDTOUpdate.builder()
//                .name("name_update")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .supplierId(1)
//                .fuelId(1)
//                .build();
//        Mockito.when(optionalValidate.getFuelImportById(1)).thenReturn(mockRepository);
//        Mockito.when(optionalValidate.getTankById(1)).thenReturn(tank);
//        Mockito.when(optionalValidate.getSupplierById(1)).thenReturn(supplier);
//        Mockito.when(optionalValidate.getFuelById(1)).thenReturn(fuel);
//        Mockito.when(fuelImportRepository.save(Mockito.any(FuelImport.class))).thenReturn(mockRepository);
//        assertEquals(mockResult, fuelImportService.update(1, fuelImportDTOUpdate));
//    }
//
//    /**
//     * param supplierId is null
//     */
//    @Test
//    void update_UTCID03() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        Tank tank = Tank.builder().id(1).name("tank").build();
//        TankDTO tankDTO = TankDTO.builder().id(1).name("tank").build();
//        Supplier supplier = Supplier.builder().id(1).name("supplier").build();
//        SupplierDTO supplierDTO = SupplierDTO.builder().id(1).name("supplier").build();
//        Fuel fuel = Fuel.builder().id(1).build();
//        FuelDTO fuelDTO = FuelDTO.builder().id(1).build();
//        FuelImport mockRepository = FuelImport.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10)
//                .unitPrice(100)
//                .amountPaid(50d)
//                .vatPercent(0)
//                .note("note")
//                .tank(tank)
//                .supplier(supplier)
//                .fuel(fuel)
//                .build();
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name_update")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(tankDTO)
//                .fuel(fuelDTO)
//                .supplier(supplierDTO)
//                .build();
//        FuelImportDTOUpdate fuelImportDTOUpdate = FuelImportDTOUpdate.builder()
//                .name("name_update")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .tankId(1)
//                .fuelId(1)
//                .build();
//        Mockito.when(optionalValidate.getFuelImportById(1)).thenReturn(mockRepository);
//        Mockito.when(optionalValidate.getTankById(1)).thenReturn(tank);
//        Mockito.when(optionalValidate.getSupplierById(1)).thenReturn(supplier);
//        Mockito.when(optionalValidate.getFuelById(1)).thenReturn(fuel);
//        Mockito.when(fuelImportRepository.save(Mockito.any(FuelImport.class))).thenReturn(mockRepository);
//        assertEquals(mockResult, fuelImportService.update(1, fuelImportDTOUpdate));
//    }
//
//    /**
//     * param fuelId is null
//     */
//    @Test
//    void update_UTCID04() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        Tank tank = Tank.builder().id(1).name("tank").build();
//        TankDTO tankDTO = TankDTO.builder().id(1).name("tank").build();
//        Supplier supplier = Supplier.builder().id(1).name("supplier").build();
//        SupplierDTO supplierDTO = SupplierDTO.builder().id(1).name("supplier").build();
//        Fuel fuel = Fuel.builder().id(1).build();
//        FuelDTO fuelDTO = FuelDTO.builder().id(1).build();
//        FuelImport mockRepository = FuelImport.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10)
//                .unitPrice(100)
//                .amountPaid(50d)
//                .vatPercent(0)
//                .note("note")
//                .tank(tank)
//                .supplier(supplier)
//                .fuel(fuel)
//                .build();
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name_update")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(tankDTO)
//                .fuel(fuelDTO)
//                .supplier(supplierDTO)
//                .build();
//        FuelImportDTOUpdate fuelImportDTOUpdate = FuelImportDTOUpdate.builder()
//                .name("name_update")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .tankId(1)
//                .supplierId(1)
//                .build();
//        Mockito.when(optionalValidate.getFuelImportById(1)).thenReturn(mockRepository);
//        Mockito.when(optionalValidate.getTankById(1)).thenReturn(tank);
//        Mockito.when(optionalValidate.getSupplierById(1)).thenReturn(supplier);
//        Mockito.when(optionalValidate.getFuelById(1)).thenReturn(fuel);
//        Mockito.when(fuelImportRepository.save(Mockito.any(FuelImport.class))).thenReturn(mockRepository);
//        assertEquals(mockResult, fuelImportService.update(1, fuelImportDTOUpdate));
//    }
//
//    @Test
//    void delete() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        Tank tank = Tank.builder().id(1).name("tank").build();
//        TankDTO tankDTO = TankDTO.builder().id(1).name("tank").build();
//        Supplier supplier = Supplier.builder().id(1).name("supplier").build();
//        SupplierDTO supplierDTO = SupplierDTO.builder().id(1).name("supplier").build();
//        Fuel fuel = Fuel.builder().id(1).build();
//        FuelDTO fuelDTO = FuelDTO.builder().id(1).build();
//        FuelImport mockRepository = FuelImport.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10)
//                .unitPrice(100)
//                .amountPaid(50d)
//                .vatPercent(0)
//                .note("note")
//                .tank(tank)
//                .supplier(supplier)
//                .fuel(fuel)
//                .build();
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .amountPaid(50d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(tankDTO)
//                .fuel(fuelDTO)
//                .supplier(supplierDTO)
//                .build();
//        Mockito.when(optionalValidate.getFuelImportById(1)).thenReturn(mockRepository);
//        assertEquals(mockResult, fuelImportService.delete(1));
//    }
//}