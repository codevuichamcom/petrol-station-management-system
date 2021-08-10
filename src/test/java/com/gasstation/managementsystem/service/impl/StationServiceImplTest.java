package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.model.mapper.StationMapper;
import com.gasstation.managementsystem.repository.StationRepository;
import com.gasstation.managementsystem.repository.UserRepository;
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

class StationServiceImplTest {
    @Mock
    private StationRepository stationRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private OptionalValidate optionalValidate;
    @InjectMocks
    private StationServiceImpl stationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param username, UserType is ADMIN
     */
    @Test
    void findAll_UTCID01() {
        List<Station> mockRepository = new ArrayList<>();
        List<StationDTO> mockResult = new ArrayList<>();

        mockData_ADMIN(mockRepository, mockResult);
        List<StationDTO> listResultService = (List<StationDTO>) stationService.findAll("ADMIN").get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            StationDTO o1 = mockResult.get(i);
            StationDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData_ADMIN(List<Station> mockRepository, List<StationDTO> mockResult) {
        UserType userType = UserType.builder().id(1).type("ADMIN").build();
        User user = User.builder().id(1).username("ADMIN").userType(userType).build();
        Mockito.when(userRepository.findByUsername("ADMIN")).thenReturn(user);
        for (int i = 1; i <= 10; i++) {
            Station station = Station.builder().id(i).owner(user).build();
            mockRepository.add(station);
            mockResult.add(StationMapper.toStationDTO(station));
        }
        Mockito.when(stationRepository.findAll()).thenReturn(mockRepository);
    }

    /**
     * param username, UserType is OWNER
     */
    @Test
    void findAll_UTCID02() {
        List<Station> mockRepository = new ArrayList<>();
        List<StationDTO> mockResult = new ArrayList<>();

        mockData_OWNER(mockRepository, mockResult);
        List<StationDTO> listResultService = (List<StationDTO>) stationService.findAll("OWNER").get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            StationDTO o1 = mockResult.get(i);
            StationDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData_OWNER(List<Station> mockRepository, List<StationDTO> mockResult) {
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        User user = User.builder().id(1).username("OWNER").userType(userType).build();
        Mockito.when(userRepository.findByUsername("OWNER")).thenReturn(user);
        for (int i = 1; i <= 10; i++) {
            Station station = Station.builder().id(i).owner(user).build();
            mockRepository.add(station);
            mockResult.add(StationMapper.toStationDTO(station));
        }
        Mockito.when(stationRepository.findByOwnerId(1)).thenReturn(mockRepository);
    }

    @Test
    void findById() throws CustomNotFoundException {
        Station mockRepository = Station.builder()
                .id(1).name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        StationDTO mockResult = StationDTO.builder()
                .id(1).name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, stationService.findById(1));
    }

    @Test
    void create_UTCID01() throws CustomBadRequestException, CustomDuplicateFieldException {
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        User user = User.builder().id(1).username("OWNER").userType(userType).build();
        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(userRepository.findById(1)).thenReturn(optionalUser);

        Station mockRepository = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
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

        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(mockRepository);
        assertEquals(mockResult, stationService.create(stationDTOCreate));
    }

    /**
     * Param User isEmpty
     */
    @Test
    void create_UTCID02() throws CustomBadRequestException, CustomDuplicateFieldException {
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        User user = User.builder().id(1).username("OWNER").userType(userType).build();
        Optional<User> optionalUser = Optional.of(user);

        Station mockRepository = Station.builder()
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

        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(mockRepository);
        assertThrows(CustomBadRequestException.class, () -> {
            stationService.create(stationDTOCreate);
        });
    }

    /**
     * Param UserType is not OWNER
     */
    @Test
    void create_UTCID03() throws CustomBadRequestException, CustomDuplicateFieldException {
        UserType userType = UserType.builder().id(4).type("CUSTOMER").build();
        User user = User.builder().id(1).username("CUSTOMER").userType(userType).build();
        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(userRepository.findById(1)).thenReturn(optionalUser);

        Station mockRepository = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
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

        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(mockRepository);
        assertThrows(CustomBadRequestException.class, () -> {
            stationService.create(stationDTOCreate);
        });
    }

    /**
     * check duplicate
     */
    @Test
    void create_UTCID04() throws CustomBadRequestException, CustomDuplicateFieldException {
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        User user = User.builder().id(1).username("OWNER").userType(userType).build();
        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(userRepository.findById(1)).thenReturn(optionalUser);

        Station mockRepository = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
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

        Optional<Station> stationOptional = Optional.of(mockRepository);

        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(mockRepository);
        Mockito.when(stationRepository.findByNameAndAddress("Hoang Long", "Me Linh- Ha Noi")).thenReturn(stationOptional);

        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(mockRepository);
        assertThrows(CustomDuplicateFieldException.class, () -> {
            stationService.create(stationDTOCreate);
        });
    }

    @Test
    void update() throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException {
        UserType userType = UserType.builder().id(2).type("OWNER").build();
        UserTypeDTO userTypeDTO = UserTypeDTO.builder().id(2).type("OWNER").build();

        User user = User.builder().id(1).username("OWNER").userType(userType).build();
        UserDTO userDTO = UserDTO.builder().id(1).username("OWNER").userType(userTypeDTO).build();

        Optional<User> userOptional = Optional.of(user);
        Mockito.when(userRepository.findById(1)).thenReturn(userOptional);

        Station mockRepository = Station.builder()
                .owner(user)
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        StationDTO mockResult = StationDTO.builder()
                .owner(userDTO)
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(100d)
                .latitude(200d)
                .build();
        //given
        StationDTOUpdate stationDTOUpdate = StationDTOUpdate.builder()
                .ownerId(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(100d)
                .latitude(200d)
                .build();

        Optional<Station> stationOptional = Optional.of(mockRepository);
        Mockito.when(stationRepository.findByNameAndAddress("Hoang Long", "Me Linh")).thenReturn(stationOptional);
        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(mockRepository);
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(mockRepository);

        StationDTO stationDTO = stationService.update(1, stationDTOUpdate);

        assertEquals(mockResult.getId(), stationDTO.getId());
        assertEquals(mockResult.getName(), stationDTO.getName());
        assertEquals(mockResult.getAddress(), stationDTO.getAddress());
        assertEquals(mockResult.getNumberOfEmployee(), stationDTO.getNumberOfEmployee());
        assertEquals(mockResult.getLatitude(), stationDTO.getLatitude());
        assertEquals(mockResult.getLongitude(), stationDTO.getLongitude());
    }

    @Test
    void delete() throws CustomNotFoundException {
        Station mockRepository = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        StationDTO mockResult = StationDTO.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        Mockito.when(optionalValidate.getStationById(1)).thenReturn(mockRepository);
        assertEquals(mockResult, stationService.delete(1));
    }
}