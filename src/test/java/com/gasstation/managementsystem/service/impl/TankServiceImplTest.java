package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTOCreate;
import com.gasstation.managementsystem.model.dto.tank.TankDTOUpdate;
import com.gasstation.managementsystem.model.mapper.TankMapper;
import com.gasstation.managementsystem.repository.FuelRepository;
import com.gasstation.managementsystem.repository.PriceChangeHistoryRepository;
import com.gasstation.managementsystem.repository.StationRepository;
import com.gasstation.managementsystem.repository.TankRepository;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
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

class TankServiceImplTest {
    @Mock
    private TankRepository tankRepository;
    @Mock
    private PriceChangeHistoryRepository priceChangeHistoryRepository;
    @Mock
    private StationRepository stationRepository;
    @Mock
    private FuelRepository fuelRepository;
    @Mock
    private OptionalValidate optionalValidate;
    @Mock
    private UserHelper userHelper;
    @InjectMocks
    private TankServiceImpl tankService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * non param
     */
    @Test
    void findAll() {
        List<Tank> mockRepository = new ArrayList<>();
        List<TankDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);

        Mockito.when(tankRepository.findAll()).thenReturn(mockRepository);

        List<TankDTO> listResultService = (List<TankDTO>) tankService.findAll().get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            TankDTO o1 = mockResult.get(i);
            TankDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<Tank> mockRepository, List<TankDTO> mockResult) {
        for (int i = 1; i <= 10; i++) {
            Tank tank = Tank.builder().id(i).build();
            mockRepository.add(tank);
            mockResult.add(TankMapper.toTankDTO(tank));
        }
    }

    @Test
    void findById() throws CustomNotFoundException {
        Tank mockRepository = Tank.builder().id(1).name("tank1")
                .volume(333d)
                .currentPrice(444d)
                .remain(0d).build();
        TankDTO mockResult = TankDTO.builder().id(1).name("tank1")
                .volume(333d)
                .currentPrice(444d)
                .remain(0d).build();
        Mockito.when(optionalValidate.getTankById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, tankService.findById(1));
    }

    @Test
    void create_UTCID01() throws CustomNotFoundException, CustomDuplicateFieldException {
        Tank mockRepository = Tank.builder().name("tank1")
                .volume(333d)
                .currentPrice(444d)
                .remain(0d)
                .build();
        TankDTO mockResult = TankDTO.builder().name("tank1")
                .volume(333d)
                .currentPrice(444d)
                .remain(0d)
                .build();
        TankDTOCreate tankDTOCreate = TankDTOCreate.builder().name("tank1").stationId(2)
                .volume(333d)
                .currentPrice(444d)
                .fuelId(3).build();

        Mockito.when(tankRepository.save(Mockito.any(Tank.class))).thenReturn(mockRepository);
        assertEquals(mockResult, tankService.create(tankDTOCreate));
    }

    /**
     * check duplicate
     */
    @Test
    void create_UTCID02() throws CustomNotFoundException {
        Station station = Station.builder().id(2).build();
        Tank tank = Tank.builder().name("tank1")
                .id(1)
                .station(station)
                .volume(333d)
                .currentPrice(444d)
                .remain(0d)
                .build();

        TankDTOCreate tankDTOCreate = TankDTOCreate.builder().name("tank1")
                .stationId(2)
                .volume(333d)
                .currentPrice(444d)
                .fuelId(3).build();

        Optional<Tank> tankOptional = Optional.of(tank);

        Mockito.when(tankRepository.save(Mockito.any(Tank.class))).thenReturn(tank);
        Mockito.when(tankRepository.findByNameAndStationId("tank1", station.getId())).thenReturn(tankOptional);
        assertThrows(CustomDuplicateFieldException.class, () -> {
            tankService.create(tankDTOCreate);
        });
    }


    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        Station station = Station.builder().id(3).build();
        Station station2 = Station.builder().id(4).build();
        Fuel fuel = Fuel.builder().id(3).build();
        Tank mockRepository = Tank.builder().name("tank1")
                .id(1)
                .station(station)
                .fuel(fuel)
                .volume(333d)
                .currentPrice(444d)
                .remain(0d)
                .build();
        TankDTO mockResult = TankDTO.builder().name("tank1")
                .id(1)
                .station(StationDTO.builder().id(3).build())
                .fuel(FuelDTO.builder().id(3).build())
                .volume(333d)
                .currentPrice(555d)
                .remain(0d)
                .build();

        TankDTOUpdate tankDTOUpdate = TankDTOUpdate.builder().name("tank1")
                .stationId(4)
                .fuelId(3)
                .volume(333d)
                .currentPrice(555d)
                .build();
        User editor = User.builder().id(2).build();
        PriceChangeHistory priceChangeHistory = PriceChangeHistory.builder()
                .time(DateTimeHelper.getCurrentUnixTime())
                .oldPrice(444d)
                .newPrice(555d)
                .editor(editor)
                .station(station)
                .tank(mockRepository).build();
        Mockito.when(userHelper.getUserLogin()).thenReturn(editor);
        Mockito.when(priceChangeHistoryRepository.save(Mockito.any(PriceChangeHistory.class))).thenReturn(priceChangeHistory);
        Mockito.when(tankRepository.save(Mockito.any(Tank.class))).thenReturn(mockRepository);
        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(station);
        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(station2);
        Mockito.when(fuelRepository.save(Mockito.any(Fuel.class))).thenReturn(fuel);

        Mockito.when(optionalValidate.getStationById(3)).thenReturn(station);
        Mockito.when(optionalValidate.getStationById(4)).thenReturn(station);
        Mockito.when(optionalValidate.getFuelById(3)).thenReturn(fuel);
        Mockito.when(optionalValidate.getTankById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, tankService.update(1, tankDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        Tank mockRepository = Tank.builder().name("tank1")
                .volume(333d)
                .currentPrice(444d)
                .remain(0d)
                .build();
        TankDTO mockResult = TankDTO.builder().name("tank1")
                .volume(333d)
                .currentPrice(444d)
                .remain(0d)
                .build();

        Mockito.when(optionalValidate.getTankById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, tankService.delete(1));
    }
}