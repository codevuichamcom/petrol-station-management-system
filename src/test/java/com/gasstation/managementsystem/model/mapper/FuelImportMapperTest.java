//package com.gasstation.managementsystem.model.mapper;
//
//import com.gasstation.managementsystem.entity.*;
//import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTO;
//import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOCreate;
//import com.gasstation.managementsystem.model.dto.fuelImport.FuelImportDTOUpdate;
//import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
//import com.gasstation.managementsystem.model.dto.tank.TankDTO;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class FuelImportMapperTest {
//    /**
//     * param fuelImport is null
//     */
//    @Test
//    void toFuelImportDTO_UTCID01() {
//        assertNull(FuelImportMapper.toFuelImportDTO(null));
//    }
//
//    /**
//     * param fuelImport is not null
//     */
//    @Test
//    void toFuelImportDTO_UTCID02() {
//        Long creatDate = 16094340000000L;
//        FuelImport fuelImport = FuelImport.builder()
//                .id(1)
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10)
//                .unitPrice(100)
//                .amountPaid(50d)
//                .vatPercent(0)
//                .note("note")
//                .tank(Tank.builder().id(1).name("tank").station(Station.builder()
//                        .id(1).name("station").owner(User.builder().id(2).name("OWNER").build()).build()).build())
//                .supplier(Supplier.builder().id(1).name("supplier").build())
//                .build();
//        FuelImportDTO fuelImportDTO = FuelImportMapper.toFuelImportDTO(fuelImport);
//        assertEquals(1, fuelImportDTO.getId());
//        assertEquals("name", fuelImportDTO.getName());
//        assertEquals(creatDate, fuelImportDTO.getCreatedDate());
//        assertEquals(10, fuelImportDTO.getVolume());
//        assertEquals(100, fuelImportDTO.getUnitPrice());
//        assertEquals(0, fuelImportDTO.getVatPercent());
//        assertEquals("note", fuelImportDTO.getNote());
//        assertAll(() -> {
//            TankDTO tankDTO = fuelImportDTO.getTank();
//            assertEquals(1, tankDTO.getId());
//            assertEquals("tank", tankDTO.getName());
//        });
//        assertAll(() -> {
//            SupplierDTO supplierDTO = fuelImportDTO.getSupplier();
//            assertEquals(1, supplierDTO.getId());
//            assertEquals("supplier", supplierDTO.getName());
//        });
//
//
//    }
//
//    /**
//     * param fuelImportDTOCreate is null
//     */
//    @Test
//    void toFuelImport_UTCID01() {
//        assertNull(FuelImportMapper.toFuelImport(null));
//    }
//
//    /**
//     * param fuelImportDTOCreate is not null
//     */
//    @Test
//    void toFuelImport_UTCID02() {
//        Long creatDate = 16094340000000L;
//        FuelImportDTOCreate fuelImportDTOCreate = FuelImportDTOCreate.builder()
//                .name("name")
//                .createdDate(creatDate)
//                .volume(10d)
//                .amountPaid(50d)
//                .unitPrice(100d)
//                .vatPercent(0d)
//                .note("note")
//                .build();
//        FuelImport fuelImport = FuelImportMapper.toFuelImport(fuelImportDTOCreate);
//        assertEquals("name", fuelImport.getName());
//        assertEquals(creatDate, fuelImport.getCreatedDate());
//        assertEquals(10, fuelImport.getVolume());
//        assertEquals(100, fuelImport.getUnitPrice());
//        assertEquals(0, fuelImport.getVatPercent());
//        assertEquals("note", fuelImport.getNote());
//    }
//
//    /**
//     * param fuelImport is null
//     */
//    @Test
//    void copyNonNullToFuelImport_UTCID01() {
//        Long dateUpdate = 16094340000000L;
//        FuelImportDTOUpdate fuelImportDTOUpdate = FuelImportDTOUpdate.builder()
//                .name("name_update")
//                .createdDate(dateUpdate)
//                .volume(11d)
//                .unitPrice(101d)
//                .amountPaid(50d)
//                .vatPercent(1d)
//                .note("note_update")
//                .build();
//        try {
//            FuelImportMapper.copyNonNullToFuelImport(null, fuelImportDTOUpdate);
//        } catch (Exception ex) {
//            assertTrue(true);
//        }
//
//    }
//
//    /**
//     * param fuelImport is not null
//     */
//    @Test
//    void copyNonNullToFuelImport_UTCID02() {
//        Long creatDate = 16094340000000L;
//        FuelImport fuelImport = FuelImport.builder()
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
//        Long dateUpdate = 16094440000000L;
//        FuelImportDTOUpdate fuelImportDTOUpdate = FuelImportDTOUpdate.builder()
//                .name("name_update")
//                .createdDate(dateUpdate)
//                .volume(11d)
//                .unitPrice(101d)
//                .vatPercent(1d)
//                .note("note_update")
//                .build();
//        FuelImportMapper.copyNonNullToFuelImport(fuelImport, fuelImportDTOUpdate);
//        assertEquals(1, fuelImport.getId());
//        assertEquals("name_update", fuelImport.getName());
//        assertEquals(dateUpdate, fuelImport.getCreatedDate());
//        assertEquals(11, fuelImport.getVolume());
//        assertEquals(101, fuelImport.getUnitPrice());
//        assertEquals(1, fuelImport.getVatPercent());
//        assertEquals("note_update", fuelImport.getNote());
//        assertAll(() -> {
//            Tank tank = fuelImport.getTank();
//            assertEquals(1, tank.getId());
//            assertEquals("tank", tank.getName());
//        });
//        assertAll(() -> {
//            Supplier supplier = fuelImport.getSupplier();
//            assertEquals(1, supplier.getId());
//            assertEquals("supplier", supplier.getName());
//        });
//
//
//    }
//}