package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdate;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.service.impl.PumpServiceImpl;
import com.gasstation.managementsystem.service.impl.StationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StationControllerTest {
    @Mock
    StationServiceImpl stationService;

    @Mock
    PumpServiceImpl pumpService;

    @InjectMocks
    StationController stationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(1).username("OWNER").userType(userTypeDTO).build();
        List<StationDTO> stationDTOList = IntStream.range(1, 10).mapToObj(i -> StationDTO.builder()
                .id(i)
                .name("Hoang Long" + i)
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .owner(userDTO)
                .build()).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", stationDTOList);
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "OWNER";
            }
        };
        Mockito.when(stationService.findAll(principal.getName())).thenReturn(map);
        HashMap<String, Object> mapResult = stationController.getAll(principal);

        assertTrue(mapResult.containsKey("data"));
        List<StationDTO> stationDTOListResult = (List<StationDTO>) mapResult.get("data");
        assertEquals(stationDTOList.size(), stationDTOListResult.size());
        for (int i = 0; i < stationDTOListResult.size(); i++) {
            StationDTO o1 = stationDTOList.get(i);
            StationDTO o2 = stationDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    @Test
    void getOne() throws CustomNotFoundException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(1).username("OWNER").userType(userTypeDTO).build();
        StationDTO mockResult = StationDTO.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .owner(userDTO)
                .build();
        Mockito.when(stationService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, stationController.getOne(1));
    }

    @Test
    void getAllPump() throws CustomNotFoundException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(1).username("OWNER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .owner(userDTO)
                .build();

        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .station(stationDTO)
                .build();

        List<PumpDTO> pumpDTOList = IntStream.range(1, 10).mapToObj(i -> PumpDTO.builder()
                .id(i).name("Pump_" + i).tank(tankDTO)
                .note("pump" + i).build()
        ).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", pumpDTOList);

        Mockito.when(pumpService.findAllByStationId(1)).thenReturn(map);

        HashMap<String, Object> mapResult = stationController.getAllPump(1);

        assertTrue(mapResult.containsKey("data"));

        List<PumpDTO> PumpDTOListResult = (List<PumpDTO>) mapResult.get("data");
        assertEquals(pumpDTOList.size(), PumpDTOListResult.size());
        for (int i = 0; i < PumpDTOListResult.size(); i++) {
            PumpDTO o1 = pumpDTOList.get(i);
            PumpDTO o2 = PumpDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    @Test
    void create() throws CustomBadRequestException, CustomDuplicateFieldException {
        StationDTO mockResult = StationDTO.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        //given
        StationDTOCreate stationDTOCreate = StationDTOCreate.builder()
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .ownerId(1)
                .build();
        Mockito.when(stationService.create(stationDTOCreate)).thenReturn(mockResult);
        assertEquals(mockResult, stationController.create(stationDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(1).username("OWNER").userType(userTypeDTO).build();
        StationDTO mockResult = StationDTO.builder()
                .owner(userDTO)
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(100d)
                .latitude(200d)
                .build();
        StationDTOUpdate stationDTOUpdate = StationDTOUpdate.builder()
                .ownerId(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(100d)
                .latitude(200d)
                .build();
        Mockito.when( stationService.update(1, stationDTOUpdate)).thenReturn(mockResult);
        assertEquals(mockResult,stationController.update(1,stationDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(1).username("OWNER").userType(userTypeDTO).build();
        StationDTO mockResult = StationDTO.builder()
                .owner(userDTO)
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(100d)
                .latitude(200d)
                .build();
        Mockito.when( stationService.delete(1)).thenReturn(mockResult);
        assertEquals(mockResult,stationController.delete(1));
    }
}