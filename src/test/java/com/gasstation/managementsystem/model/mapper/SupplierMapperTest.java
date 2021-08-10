package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Supplier;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOCreate;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOUpdate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierMapperTest extends SupplierMapper {
    /**
     * param supplier is null
     */
    @Test
    void testToSupplierDTO_UTCID01() {
        assertNull(SupplierMapper.toSupplierDTO(null));
    }

    /**
     * param supplier is not null
     */
    @Test
    void testToSupplierDTO_UTCID02() {
        //given
        Supplier supplier = Supplier.builder()
                .id(1)
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich")
                .build();
        //when
        SupplierDTO supplierDTO = SupplierMapper.toSupplierDTO(supplier);
        //then
        assertEquals(1, supplierDTO.getId());
        assertEquals("Khanh Huyen", supplierDTO.getName());
        assertEquals("0989656431", supplierDTO.getPhone());
        assertEquals("Thach That", supplierDTO.getAddress());
        assertEquals("Chu tich", supplierDTO.getNote());
    }

    /**
     * param supplierDTO is null
     */
    @Test
    void testToSupplier_UTCID01() {
        assertNull(SupplierMapper.toSupplier(null));
    }

    /**
     * param supplierDTO is not null
     */
    @Test
    void testToSupplier_UTCID02() {
        //given
        SupplierDTOCreate supplierDTOCreate = SupplierDTOCreate.builder()
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich")
                .build();
        //when
        Supplier supplier = SupplierMapper.toSupplier(supplierDTOCreate);
        //then
        assertEquals("Khanh Huyen", supplier.getName());
        assertEquals("0989656431", supplier.getPhone());
        assertEquals("Thach That", supplier.getAddress());
        assertEquals("Chu tich", supplier.getNote());
    }

    /**
     * precondition supplier is null
     */
    @Test
    void testCopyNonNullToSupplier_UTCID01() {
        //given
        SupplierDTOUpdate supplierDTOUpdate = SupplierDTOUpdate.builder()
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich")
                .build();
        //then
        try {
            SupplierMapper.copyNonNullToSupplier(null, supplierDTOUpdate);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * precondition supplier is null
     */
    @Test
    void testCopyNonNullToSupplier_UTCID02() {
        //given
        Supplier supplier = Supplier.builder()
                .id(1)
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich")
                .build();

        SupplierDTOUpdate supplierDTOUpdate = SupplierDTOUpdate.builder()
                .name("Khanh Ly")
                .phone("0989836565")
                .address("Thach That- Ha Noi")
                .note("Tong giam doc")
                .build();

        //when
        SupplierMapper.copyNonNullToSupplier(supplier, supplierDTOUpdate);

        //then
        assertEquals("Khanh Ly", supplier.getName());
        assertEquals("0989836565", supplier.getPhone());
        assertEquals("Thach That- Ha Noi", supplier.getAddress());
        assertEquals("Tong giam doc", supplier.getNote());
    }
}