package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.service.impl.PumpServiceImpl;
import com.gasstation.managementsystem.utils.UserHelper;
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

class PumpControllerTest {
    @Mock
    PumpServiceImpl pumpService;
    @Mock
    UserHelper userHelper;
    @InjectMocks
    PumpController pumpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param login is ADMIN
     */
    @Test
    void getAll_UTCID01() {
        UserType userType = UserType.builder().id(1).type("ADMIN").build();
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(1).type("ADMIN").build();
        UserDTO userDTO = UserDTO.builder().id(1).name("ADMIN").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).owner(userDTO).build();

        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .station(stationDTO)
                .build();
        List<PumpDTO> pumpDTOList = IntStream.range(1, 10).mapToObj(i -> PumpDTO.builder()
                .id(i)
                .tank(tankDTO)
                .name("pump" + i)
                .note("pump" + i)
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", pumpDTOList);
        Mockito.when(userHelper.getUserTypeOfUserLogin()).thenReturn(userType);
        Mockito.when(pumpService.findAll()).thenReturn(map);
        HashMap<String, Object> mapResult = pumpController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<PumpDTO> PumpDTOListResult = (List<PumpDTO>) mapResult.get("data");
        assertEquals(pumpDTOList.size(), PumpDTOListResult.size());
        for (int i = 0; i < PumpDTOListResult.size(); i++) {
            PumpDTO o1 = pumpDTOList.get(i);
            PumpDTO o2 = PumpDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    /**
     * param login is OWNER
     */
    @Test
    void getAll_UTCID02() {
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(2).name("OWNER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).owner(userDTO).build();

        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .station(stationDTO)
                .build();
        List<PumpDTO> pumpDTOList = IntStream.range(1, 10).mapToObj(i -> PumpDTO.builder()
                .id(i)
                .tank(tankDTO)
                .name("pump" + i)
                .note("pump" + i)
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", pumpDTOList);
        Mockito.when(userHelper.getUserTypeOfUserLogin()).thenReturn(userType);
        Mockito.when(pumpService.findAllByOwnerId(2)).thenReturn(map);
        HashMap<String, Object> mapResult = pumpController.getAll();

        assertTrue(mapResult.containsKey("data"));
        List<PumpDTO> PumpDTOListResult = (List<PumpDTO>) mapResult.get("data");
        assertEquals(pumpDTOList.size(), PumpDTOListResult.size());
        for (int i = 0; i < PumpDTOListResult.size(); i++) {
            PumpDTO o1 = pumpDTOList.get(i);
            PumpDTO o2 = PumpDTOListResult.get(i);
            assertEquals(o1, o2);
        }
    }

    /**
     * param login is not ADMIN,OWNER
     */
    @Test
    void getAll_UTCID03() {
        UserType userType = UserType.builder().id(3).type("CUSTOMER").build();
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(3).type("CUSTOMER").build();
        UserDTO userDTO = UserDTO.builder().id(1).name("CUSTOMER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).owner(userDTO).build();

        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .station(stationDTO)
                .build();
        List<PumpDTO> pumpDTOList = IntStream.range(1, 10).mapToObj(i -> PumpDTO.builder()
                .id(i)
                .tank(tankDTO)
                .name("pump" + i)
                .note("pump" + i)
                .build()).collect(Collectors.toList());

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", pumpDTOList);
        Mockito.when(userHelper.getUserTypeOfUserLogin()).thenReturn(userType);
        Mockito.when(pumpService.findAll()).thenReturn(map);
        HashMap<String, Object> mapResult = pumpController.getAll();
    }

    @Test
    void getOne() throws CustomNotFoundException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(2).name("OWNER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).owner(userDTO).build();

        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .station(stationDTO)
                .build();
        PumpDTO mockResult = PumpDTO.builder()
                .id(1)
                .tank(tankDTO)
                .name("pump1")
                .note("pump1")
                .build();
        Mockito.when(pumpService.findById(1)).thenReturn(mockResult);
        assertEquals(mockResult, pumpController.getOne(1));
    }

    @Test
    void create() throws CustomNotFoundException, CustomDuplicateFieldException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(2).name("OWNER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).owner(userDTO).build();

        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .station(stationDTO)
                .build();
        PumpDTO mockResult = PumpDTO.builder()
                .tank(tankDTO)
                .name("pump1")
                .note("pump1")
                .build();
        PumpDTOCreate pumpDTOCreate = PumpDTOCreate.builder()
                .tankId(1)
                .name("pump1")
                .note("pump1")
                .build();
        Mockito.when(pumpService.create(pumpDTOCreate)).thenReturn(mockResult);
        assertEquals(mockResult, pumpController.create(pumpDTOCreate));
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(2).name("OWNER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).owner(userDTO).build();

        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .station(stationDTO)
                .build();
        PumpDTO mockResult = PumpDTO.builder()
                .tank(tankDTO)
                .name("pump1")
                .note("pump1")
                .build();
        PumpDTOUpdate pumpDTOUpdate = PumpDTOUpdate.builder()
                .tankId(1)
                .name("pump1")
                .note("pump1")
                .build();
        Mockito.when(pumpService.update(1, pumpDTOUpdate)).thenReturn(mockResult);
        assertEquals(mockResult, pumpController.update(1, pumpDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();
        UserDTO userDTO = UserDTO.builder().id(2).name("OWNER").userType(userTypeDTO).build();
        StationDTO stationDTO = StationDTO.builder().id(1).owner(userDTO).build();

        TankDTO tankDTO = TankDTO.builder().name("tank1")
                .id(1)
                .station(stationDTO)
                .build();
        PumpDTO mockResult = PumpDTO.builder()
                .id(1)
                .tank(tankDTO)
                .name("pump1")
                .note("pump1")
                .build();
        Mockito.when(pumpService.delete(1)).thenReturn(mockResult);
        assertEquals(mockResult, pumpController.delete(1));
    }
}