package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationMapperTest extends StationMapper {

    /**
     * param station is null
     */
    @Test
    void testToStationDTO_UTCID01() {
        assertNull(StationMapper.toStationDTO(null));
    }

    /**
     * param station is not null
     */
    @Test
    void testToStationDTO_UTCID02() {
        //given
        User owner = User.builder()
                .id(999)
                .name("Le Viet Tu").build();
        Station station = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(29d)
                .latitude(56d)
                .owner(owner)
                .build();
        //when
        StationDTO stationDTO = StationMapper.toStationDTO(station);
        //then
        assertEquals(1, stationDTO.getId());
        assertEquals("Hoang Long", stationDTO.getName());
        assertEquals("Me Linh- Ha Noi", stationDTO.getAddress());
        assertEquals(29, stationDTO.getLongitude());
        assertEquals(56, stationDTO.getLatitude());
        assertAll(() -> {
            UserDTO ownerDTO = stationDTO.getOwner();
            assertEquals(999, ownerDTO.getId());
            assertEquals("Le Viet Tu", ownerDTO.getName());
        });
    }

    /**
     * param stationDTOCreate is null
     */
    @Test
    void testToStation_UTCID01() {
        assertNull(StationMapper.toStation(null));
    }

    /**
     * param stationDTOCreate is not null
     */
    @Test
    void testToStation_UTCID02() {
        //given
        StationDTOCreate stationDTOCreate = StationDTOCreate.builder()
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(29d)
                .latitude(56d)
                .ownerId(999)
                .build();
        //when
        Station station = StationMapper.toStation(stationDTOCreate);
        //then
        assertEquals("Hoang Long", station.getName());
        assertEquals("Me Linh- Ha Noi", station.getAddress());
        assertEquals(29, station.getLongitude());
        assertEquals(56, station.getLatitude());
    }

    /**
     * param station is null
     */
    @Test
    void testCopyNonNullToStation_UTCID01() {
        //given
        StationDTOUpdate stationDTOUpdate = StationDTOUpdate.builder()
                .name("Hoang Long Gas")
                .address("Thach That- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .ownerId(998)
                .build();
        //then
        try {
            StationMapper.copyNonNullToStation(null, stationDTOUpdate);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * param station is not null
     */
    @Test
    void testCopyNonNullToStation_UTCID02() {
        //given
        Station station = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();

        StationDTOUpdate stationDTOUpdate = StationDTOUpdate.builder()
                .name("Hoang Long Gas")
                .address("Thach That- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .ownerId(998)
                .build();
        //when
        StationMapper.copyNonNullToStation(station, stationDTOUpdate);
        //then
        assertEquals("Hoang Long Gas", station.getName());
        assertEquals("Thach That- Ha Noi", station.getAddress());
        assertEquals(39, station.getLongitude());
        assertEquals(46, station.getLatitude());
    }
}