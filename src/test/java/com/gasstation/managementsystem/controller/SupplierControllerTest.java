package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOCreate;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOUpdate;
import com.gasstation.managementsystem.service.impl.SupplierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SupplierControllerTest {
    @Mock
    SupplierServiceImpl supplierService;

    @InjectMocks
    SupplierController supplierController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        List<SupplierDTO> supplierDTOList = IntStream.range(1, 10).mapToObj(i -> SupplierDTO.builder()
                .id(i)
                .name("supplier" + i)
                .phone("012345678" + i)
                .address("address" + i)
                .note("note" + i)
                .build()).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", supplierDTOList);
        Mockito.when(supplierService.findAll()).thenReturn(map);
        HashMap<String, Object> mapResult = supplierController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<SupplierDTO> supplierDTOListResult = (List<SupplierDTO>) mapResult.get("data");
        assertEquals(supplierDTOList.size(), supplierDTOListResult.size());
        for (int i = 0; i < supplierDTOListResult.size(); i++) {
            SupplierDTO o1 = supplierDTOList.get(i);
            SupplierDTO o2 = supplierDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        SupplierDTO mockResult = SupplierDTO.builder()
                .id(1)
                .name("supplier_1")
                .phone("0123456789")
                .address("address")
                .note("note")
                .build();
        Mockito.when(supplierService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, supplierController.getOne(1));
    }

    @Test
    void create() throws CustomDuplicateFieldException {
        //given
        SupplierDTO mockResult = SupplierDTO.builder()
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich")
                .build();

        SupplierDTOCreate supplierDTOCreate = SupplierDTOCreate.builder()
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich")
                .build();
        Mockito.when(supplierService.create(supplierDTOCreate)).thenReturn(mockResult);
        assertEquals(mockResult, supplierController.create(supplierDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        //given
        SupplierDTO mockResult = SupplierDTO.builder()
                .id(1)
                .name("Khanh Ly")
                .phone("0989656431")
                .address("Thach That- Ha Noi")
                .note("Chu tich HDQT")
                .build();

        SupplierDTOUpdate supplierDTOUpdate = SupplierDTOUpdate.builder()
                .name("Khanh Ly")
                .phone("0989656431")
                .address("Thach That- Ha Noi")
                .note("Chu tich HDQT")
                .build();

        Mockito.when(supplierService.update(1, supplierDTOUpdate)).thenReturn(mockResult);
        assertEquals(mockResult, supplierController.update(1, supplierDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        SupplierDTO mockResult = SupplierDTO.builder()
                .id(1)
                .name("Khanh Ly")
                .phone("0989656431")
                .address("Thach That- Ha Noi")
                .note("Chu tich HDQT")
                .build();

        Mockito.when(supplierService.delete(1)).thenReturn(mockResult);
        assertEquals(mockResult, supplierController.delete(1));
    }
}