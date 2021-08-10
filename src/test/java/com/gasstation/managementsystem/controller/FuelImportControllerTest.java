//package com.gasstation.managementsystem.controller;
//
//import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
//import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
//import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOCreate;
//import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOUpdate;
//import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
//import com.gasstation.managementsystem.model.dto.tank.TankDTO;
//import com.gasstation.managementsystem.service.impl.FuelImportServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class FuelImportControllerTest {
//    @Mock
//    FuelImportServiceImpl fuelImportService;
//
//    @InjectMocks
//    FuelImportController fuelImportController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    /**
//     * param pageSize is not null
//     */
//
//    @Test
//    void getAll_UTCID01() {
//        Long creatDate = 16094340000000L;
//        List<FuelImportDTO> fuelImportDTOList = IntStream.range(1, 10).mapToObj(i -> FuelImportDTO.builder()
//                .id(i)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(TankDTO.builder().id(1).name("tank").build())
//                .supplier(SupplierDTO.builder().id(1).name("supplier").build())
//                .build()).collect(Collectors.toList());
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("data", fuelImportDTOList);
//        final int PAGE_INDEX = 1;
//        final int PAGE_SIZE = 3;
//        Mockito.when(fuelImportService.findAll(PageRequest.of(PAGE_INDEX - 1, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "id")))).thenReturn(map);
//        HashMap<String, Object> mapResult = fuelImportController.getAll(PAGE_INDEX, PAGE_SIZE);
//
//        assertTrue(mapResult.containsKey("data"));
//        List<FuelImportDTO> fuelImportDTOSListResult = (List<FuelImportDTO>) mapResult.get("data");
//        assertEquals(fuelImportDTOList.size(), fuelImportDTOSListResult.size());
//        for (int i = 0; i < fuelImportDTOSListResult.size(); i++) {
//            FuelImportDTO o1 = fuelImportDTOList.get(i);
//            FuelImportDTO o2 = fuelImportDTOSListResult.get(i);
//            assertEquals(o1, o2);
//        }
//    }
//
//    /**
//     * param pageSize is null
//     */
//    @Test
//    void getAll_UTCID02() {
//        Long creatDate = 16094340000000L;
//        List<FuelImportDTO> fuelImportDTOList = IntStream.range(1, 10).mapToObj(i -> FuelImportDTO.builder()
//                .id(i)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(TankDTO.builder().id(1).name("tank").build())
//                .supplier(SupplierDTO.builder().id(1).name("supplier").build())
//                .build()).collect(Collectors.toList());
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("data", fuelImportDTOList);
//        final int PAGE_INDEX = 1;
//        Mockito.when(fuelImportService.findAll()).thenReturn(map);
//        HashMap<String, Object> mapResult = fuelImportController.getAll(PAGE_INDEX, null);
//
//        assertTrue(mapResult.containsKey("data"));
//        List<FuelImportDTO> fuelImportDTOSListResult = (List<FuelImportDTO>) mapResult.get("data");
//        assertEquals(fuelImportDTOList.size(), fuelImportDTOSListResult.size());
//        for (int i = 0; i < fuelImportDTOSListResult.size(); i++) {
//            FuelImportDTO o1 = fuelImportDTOList.get(i);
//            FuelImportDTO o2 = fuelImportDTOSListResult.get(i);
//            assertEquals(o1, o2);
//        }
//    }
//
//    @Test
//    void getOne() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(TankDTO.builder().id(1).name("tank").build())
//                .supplier(SupplierDTO.builder().id(1).name("supplier").build())
//                .build();
//        Mockito.when(fuelImportService.findById(1)).thenReturn(mockResult);
//        assertEquals(mockResult, fuelImportController.getOne(1));
//    }
//
//    @Test
//    void create() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(TankDTO.builder().id(1).name("tank").build())
//                .supplier(SupplierDTO.builder().id(1).name("supplier").build())
//                .build();
//        FuelImportDTOCreate fuelImportDTOCreate = FuelImportDTOCreate.builder()
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .vatPercent(0d)
//                .note("note")
//                .tankId(1)
//                .supplierId(1)
//                .build();
//        Mockito.when(fuelImportService.create(fuelImportDTOCreate)).thenReturn(mockResult);
//        assertEquals(mockResult, fuelImportController.create(fuelImportDTOCreate));
//    }
//
//    @Test
//    void update() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(TankDTO.builder().id(1).name("tank").build())
//                .supplier(SupplierDTO.builder().id(1).name("supplier").build())
//                .build();
//        FuelImportDTOUpdate fuelImportDTOUpdate = FuelImportDTOUpdate.builder()
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .vatPercent(0d)
//                .note("note")
//                .tankId(1)
//                .supplierId(1)
//                .build();
//        Mockito.when(fuelImportService.update(1, fuelImportDTOUpdate)).thenReturn(mockResult);
//        assertEquals(mockResult, fuelImportController.update(1, fuelImportDTOUpdate));
//    }
//
//    @Test
//    void delete() throws CustomNotFoundException {
//        Long creatDate = 16094340000000L;
//        FuelImportDTO mockResult = FuelImportDTO.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .unitPrice(100d)
//                .vatPercent(0d)
//                .note("note")
//                .tank(TankDTO.builder().id(1).name("tank").build())
//                .supplier(SupplierDTO.builder().id(1).name("supplier").build())
//                .build();
//        Mockito.when(fuelImportService.delete(1)).thenReturn(mockResult);
//        assertEquals(mockResult, fuelImportController.delete(1));
//    }
//}