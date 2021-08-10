package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Supplier;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTO;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOCreate;
import com.gasstation.managementsystem.model.dto.supplier.SupplierDTOUpdate;
import com.gasstation.managementsystem.model.mapper.SupplierMapper;
import com.gasstation.managementsystem.repository.SupplierRepository;
import com.gasstation.managementsystem.utils.OptionalValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SupplierServiceImplTest {
    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private OptionalValidate optionalValidate;
    @InjectMocks
    private SupplierServiceImpl supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param Sort
     */
    @Test
    void findAll() {
        List<Supplier> mockRepository = new ArrayList<>();
        List<SupplierDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);
        List<SupplierDTO> listResultService = (List<SupplierDTO>) supplierService.findAll().get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            SupplierDTO o1 = mockResult.get(i);
            SupplierDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<Supplier> mockRepository, List<SupplierDTO> mockResult) {
        for (int i = 1; i <= 10; i++) {
            Supplier supplier = Supplier.builder().id(i).build();
            mockRepository.add(supplier);
            mockResult.add(SupplierMapper.toSupplierDTO(supplier));
        }
    }

    @Test
    void findById() throws CustomNotFoundException {
        Supplier mockRepository = Supplier.builder().id(1)
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich").build();
        SupplierDTO mockResult = SupplierDTO.builder().id(1)
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich").build();
        Mockito.when(optionalValidate.getSupplierById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, supplierService.findById(1));
    }

    @Test
    void create_UTCID01() throws CustomDuplicateFieldException {
        //given

        Supplier mockRepository = Supplier.builder()
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich")
                .build();

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


        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(mockRepository);
        assertEquals(mockResult, supplierService.create(supplierDTOCreate));
    }

    /**
     * check duplicate
     */
    @Test
    void create_UTCID02() throws CustomDuplicateFieldException {
        //given

        Supplier mockRepository = Supplier.builder()
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich")
                .build();

        Optional<Supplier> supplierOptional = Optional.of(mockRepository);

        SupplierDTOCreate supplierDTOCreate = SupplierDTOCreate.builder()
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich")
                .build();


        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(mockRepository);
        Mockito.when(supplierRepository.findByPhone("0989656431")).thenReturn(supplierOptional);
        assertThrows(CustomDuplicateFieldException.class, () -> {
            supplierService.create(supplierDTOCreate);
        });
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        //given

        Supplier mockRepository = Supplier.builder()
                .id(1)
                .name("Khanh Huyen")
                .phone("0989656431")
                .address("Thach That")
                .note("Chu tich")
                .build();

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

        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(mockRepository);
        Mockito.when(optionalValidate.getSupplierById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, supplierService.update(1, supplierDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        //given

        Supplier mockRepository = Supplier.builder()
                .id(1)
                .name("Khanh Ly")
                .phone("0989656431")
                .address("Thach That- Ha Noi")
                .note("Chu tich HDQT")
                .build();


        SupplierDTO mockResult = SupplierDTO.builder()
                .id(1)
                .name("Khanh Ly")
                .phone("0989656431")
                .address("Thach That- Ha Noi")
                .note("Chu tich HDQT")
                .build();

        Mockito.when(optionalValidate.getSupplierById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, supplierService.delete(1));
    }
}