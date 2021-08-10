package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.mapper.PumpMapper;
import com.gasstation.managementsystem.repository.PumpRepository;
import com.gasstation.managementsystem.utils.OptionalValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PumpServiceImplTest {
    @Mock
    private PumpRepository pumpRepository;

    @Mock
    private OptionalValidate optionalValidate;

    @InjectMocks
    private PumpServiceImpl pumpService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param sort
     */
    @Test
    void findAll() {
        List<Pump> mockRepository = new ArrayList<>();
        List<PumpDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);

        Mockito.when(pumpRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(mockRepository);
        List<PumpDTO> listResultService = (List<PumpDTO>) pumpService.findAll().get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            PumpDTO o1 = mockResult.get(i);
            PumpDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<Pump> mockRepository, List<PumpDTO> mockResult) {
        User owner = User.builder().id(3).name("OWNER").build();
        Station station = Station.builder().id(2).name("STATION").owner(owner).build();

        for (int i = 1; i <= 10; i++) {
            Tank tank = Tank.builder().name("tank1")
                    .id(i)
                    .station(station)
                    .build();
            Pump pump = Pump.builder().id(i).name("Pump_" + i).tank(tank)
                    .note("pump" + i).build();
            mockRepository.add(pump);
            mockResult.add(PumpMapper.toPumpDTO(pump));
        }
    }

    @Test
    void findAllByStationId() {
        User user = User.builder().id(1).build();
        Station station = Station.builder().id(1).owner(user).build();
        StationDTO stationDTO = StationDTO.builder().id(1).build();

        Tank tank = Tank.builder().name("tank1")
                .id(1)
                .station(station)
                .build();
        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .station(stationDTO)
                .build();
        Pump mockRepository = Pump.builder()
                .id(1)
                .tank(tank)
                .name("pump1")
                .note("pump1").build();

        PumpDTO mockResult = PumpDTO.builder()
                .id(1)
                .tank(tankDTO)
                .name("pump1")
                .note("pump1").build();

        List<Pump> pumpList = new ArrayList<>();
        pumpList.add(mockRepository);
        Mockito.when(pumpRepository.save(Mockito.any(Pump.class))).thenReturn(mockRepository);
        Mockito.when(pumpService.findAllByStationId(1).get("data")).thenReturn(pumpList);
        List<PumpDTO> listResultService = (List<PumpDTO>) pumpService.findAllByStationId(1).get("data");
        assertEquals(mockResult, listResultService.get(0));
    }

    @Test
    void findAllByOwnerId() throws CustomNotFoundException {
        User user = User.builder().id(1).build();
        Station station = Station.builder().id(1).owner(user).build();
        StationDTO stationDTO = StationDTO.builder().id(1).build();

        Tank tank = Tank.builder().name("tank1")
                .id(1)
                .station(station)
                .build();
        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .station(stationDTO)
                .build();
        Pump mockRepository = Pump.builder()
                .id(1)
                .tank(tank)
                .name("pump1")
                .note("pump1").build();

        PumpDTO mockResult = PumpDTO.builder()
                .id(1)
                .tank(tankDTO)
                .name("pump1")
                .note("pump1").build();

        List<Pump> pumpList = new ArrayList<>();
        pumpList.add(mockRepository);
        Mockito.when(pumpRepository.save(Mockito.any(Pump.class))).thenReturn(mockRepository);
        Mockito.when(pumpService.findAllByOwnerId(1).get("data")).thenReturn(pumpList);
        List<PumpDTO> listResultService = (List<PumpDTO>) pumpService.findAllByOwnerId(1).get("data");
        assertEquals(mockResult, listResultService.get(0));
    }

    @Test
    void findById() throws CustomNotFoundException {
        Pump mockRepository = Pump.builder().id(1).name("tank1").note("pump1").build();
        PumpDTO mockResult = PumpDTO.builder().id(1).name("tank1").note("pump1").build();
        Mockito.when(optionalValidate.getPumpById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, pumpService.findById(1));
    }

    @Test
    void create_UTCID01() throws CustomNotFoundException, CustomDuplicateFieldException {
        Tank tank = Tank.builder().name("tank1")
                .id(1)
                .build();
        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .build();
        Pump mockRepository = Pump.builder()
                .id(1)
                .tank(tank)
                .name("Pump_1")
                .note("pump1").build();

        PumpDTO mockResult = PumpDTO.builder()
                .id(1)
                .tank(tankDTO)
                .name("Pump_1")
                .note("pump1").build();

        PumpDTOCreate pumpDTOCreate = PumpDTOCreate.builder()
                .tankId(1)
                .name("Pump_1")
                .note("pump1").build();
        Mockito.when(pumpRepository.save(Mockito.any(Pump.class))).thenReturn(mockRepository);
        assertEquals(mockResult, pumpService.create(pumpDTOCreate));
    }

    /**
     * check duplicate
     */
    @Test
    void create_UTCID02() throws CustomNotFoundException, CustomDuplicateFieldException {
        Tank tank = Tank.builder().name("tank1")
                .id(1)
                .build();

        Pump pump = Pump.builder()
                .id(1)
                .tank(tank)
                .name("Pump_1")
                .note("pump1").build();

        PumpDTOCreate pumpDTOCreate = PumpDTOCreate.builder()
                .tankId(1)
                .name("Pump_1")
                .note("pump1").build();

        Optional<Pump> pumpOptional = Optional.of(pump);

        Mockito.when(pumpRepository.findByNameAndTankId("Pump_1", 1)).thenReturn(pumpOptional);
        assertThrows(CustomDuplicateFieldException.class, () -> {
            pumpService.create(pumpDTOCreate);
        });
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .build();

        Tank tank = Tank.builder().name("tank1")
                .id(1)
                .build();

        Pump mockRepository = Pump.builder()
                .id(1)
                .tank(tank)
                .name("Pump_1")
                .note("pump1").build();

        PumpDTO mockResult = PumpDTO.builder()
                .id(1)
                .tank(tankDTO)
                .name("Pump_update")
                .note("pump1").build();

        PumpDTOUpdate pumpDTOUpdate = PumpDTOUpdate.builder()
                .tankId(1)
                .name("Pump_update")
                .note("pump1").build();

        Mockito.when(pumpRepository.save(Mockito.any(Pump.class))).thenReturn(mockRepository);
        Mockito.when(optionalValidate.getPumpById(1)).thenReturn(mockRepository);
        Mockito.when(optionalValidate.getTankById(1)).thenReturn(tank);
        assertEquals(mockResult, pumpService.update(1, pumpDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        Tank tank = Tank.builder().name("tank1")
                .id(1)
                .build();
        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .build();
        Pump mockRepository = Pump.builder()
                .id(1)
                .tank(tank)
                .name("Pump_1")
                .note("pump1").build();

        PumpDTO mockResult = PumpDTO.builder()
                .id(1)
                .tank(tankDTO)
                .name("Pump_1")
                .note("pump1").build();

        Mockito.when(optionalValidate.getPumpById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, pumpService.delete(1));
    }
}