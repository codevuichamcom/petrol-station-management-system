package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOCreate;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOUpdate;
import com.gasstation.managementsystem.model.mapper.FuelMapper;
import com.gasstation.managementsystem.repository.FuelRepository;
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

class FuelServiceImplTest {
    @Mock
    private FuelRepository fuelRepository;

    @Mock
    private OptionalValidate optionalValidate;

    @InjectMocks
    private FuelServiceImpl fuelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * non param
     */
    @Test
    void findAll_UTCID01() {
        List<Fuel> mockRepository = new ArrayList<>();
        List<FuelDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);

        Mockito.when(fuelRepository.findAll()).thenReturn(mockRepository);

        List<FuelDTO> listResultService = (List<FuelDTO>) fuelService.findAll().get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            FuelDTO o1 = mockResult.get(i);
            FuelDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<Fuel> mockRepository, List<FuelDTO> mockResult) {
        for (int i = 1; i <= 10; i++) {
            Fuel fuel = Fuel.builder().id(i).name("RON95").unit("litter").type("Xăng").price(21000d).build();
            mockRepository.add(fuel);
            mockResult.add(FuelMapper.toFuelDTO(fuel));
        }
    }

    @Test
    void findById() throws CustomNotFoundException {
        Fuel mockRepository = Fuel.builder().id(1).name("RON95").unit("litter").type("Xăng").price(21000d).build();
        FuelDTO mockResult = FuelDTO.builder().id(1).name("RON95").unit("litter").type("Xăng").price(21000d).build();
        Mockito.when(optionalValidate.getFuelById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, fuelService.findById(1));
    }

    @Test
    void create_UTCID01() throws CustomDuplicateFieldException {
        Fuel mockRepository = Fuel.builder().id(1).name("RON95").unit("litter").type("Xăng").price(21000d).build();
        FuelDTO mockResult = FuelDTO.builder().id(1).name("RON95").unit("litter").type("Xăng").price(21000d).build();
        FuelDTOCreate fuelDTOCreate = FuelDTOCreate.builder().name("RON95").unit("litter").type("Xăng").price(21000d).build();
        Mockito.when(fuelRepository.save(Mockito.any(Fuel.class))).thenReturn(mockRepository);
        assertEquals(mockResult, fuelService.create(fuelDTOCreate));
    }

    /**
     * check duplicate
     */
    @Test
    void create_UTCID02() throws CustomDuplicateFieldException {
        Fuel mockRepository = Fuel.builder().id(1).name("RON95").unit("litter").type("Xăng").price(21000d).build();
        FuelDTOCreate fuelDTOCreate = FuelDTOCreate.builder().name("RON95").unit("litter").type("Xăng").price(21000d).build();
        Mockito.when(fuelRepository.save(Mockito.any(Fuel.class))).thenReturn(mockRepository);

        Optional<Fuel> fuelOptional = Optional.of(mockRepository);
        Mockito.when(fuelRepository.findByNameContainingIgnoreCase("RON95")).thenReturn(fuelOptional);
        assertThrows(CustomDuplicateFieldException.class, () -> {
            fuelService.create(fuelDTOCreate);
        });
    }
    @Test
    void update_UTCID01() throws CustomNotFoundException, CustomDuplicateFieldException {
        Fuel mockRepository = Fuel.builder().id(1).name("RON95").unit("litter").type("Xăng").price(21000d).build();
        FuelDTOUpdate fuelDTOUpdate = FuelDTOUpdate.builder().name("RON95").unit("litter").type("gas").price(22000d).build();
        FuelDTO mockResult = FuelDTO.builder().id(1).name("RON95").unit("litter").type("gas").price(22000d).build();

        Mockito.when(fuelRepository.save(Mockito.any(Fuel.class))).thenReturn(mockRepository);
        Mockito.when(optionalValidate.getFuelById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, fuelService.update(1, fuelDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        Fuel mockRepository = Fuel.builder().id(1).name("RON95").unit("litter").type("Xăng").price(21000d).build();
        FuelDTO mockResult = FuelDTO.builder().id(1).name("RON95").unit("litter").type("Xăng").price(21000d).build();
        Mockito.when(optionalValidate.getFuelById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, fuelService.delete(1));
    }
}