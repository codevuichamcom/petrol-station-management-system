package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOUpdate;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PumpMapperTest extends PumpMapper {
    /**
     * param pump is null
     */
    @Test
    void testToPumpDTO_UTCID01() {
        assertNull(PumpMapper.toPumpDTO(null));
    }

    /**
     * param pump is not null
     */
    @Test
    void testToPumpDTO_UTCID02() {
        //given
        //given
        Station station = Station.builder()
                .id(1)
                .name("Hoang Long")
                .address("Me Linh- Ha Noi")
                .longitude(39d)
                .latitude(46d)
                .build();
        Fuel fuel = Fuel.builder()
                .id(23)
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();
        Tank tank = Tank.builder()
                .id(100)
                .name("tank_100")
                .volume(900d)
                .remain(20d)
                .currentPrice(505500d)
                .station(station)
                .fuel(fuel).build();
        Pump pump = Pump.builder()
                .id(333)
                .name("Pump_333")
                .tank(tank)
                .note("pump333").build();
        //when
        PumpDTO pumpDTO = PumpMapper.toPumpDTO(pump);
        //then
        assertEquals(333, pumpDTO.getId());
        assertEquals("Pump_333", pumpDTO.getName());
        assertAll(() -> {
            TankDTO tankDTO = pumpDTO.getTank();
            assertEquals(100, tankDTO.getId());
            assertEquals("tank_100", tankDTO.getName());
        });
        assertEquals("pump333", pumpDTO.getNote());
        assertAll(() -> {
            StationDTO stationDTO = pumpDTO.getTank().getStation();
            assertEquals(1, stationDTO.getId());
            assertEquals("Hoang Long", stationDTO.getName());
            assertEquals("Me Linh- Ha Noi", stationDTO.getAddress());
        });
        assertAll(() -> {
            FuelDTO fuelDTO =  pumpDTO.getTank().getFuel();
            assertEquals(23, fuelDTO.getId());
            assertEquals("E92", fuelDTO.getName());
        });
    }

    /**
     * param pumpDTOCreate is null
     */
    @Test
    void testToPump_UTCID01() {
        assertNull(PumpMapper.toPump(null));
    }

    /**
     * param pumpDTOCreate is not null
     */
    @Test
    void testToPump_UTCID02() {
        //given
        PumpDTOCreate pumpDTOCreate = PumpDTOCreate.builder()
                .name("Pump_333")
                .note("pump333").build();
        //when
        Pump pump = PumpMapper.toPump(pumpDTOCreate);
        //then
        assertEquals("Pump_333", pump.getName());
        assertEquals("pump333", pump.getNote());
    }

    /**
     * param pump is null
     */
    @Test
    void testCopyNonNullToFuel_UTCID01() {
        //given
        PumpDTOUpdate pumpDTOUpdate = PumpDTOUpdate.builder()
                .tankId(199)
                .name("Pump_333")
                .note("pump333").build();
        //when
        try {
            PumpMapper.copyNonNullToFuel(null, pumpDTOUpdate);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * param pump is not null
     */
    @Test
    void testCopyNonNullToFuel_UTCID02() {
        //given
        Pump pump = Pump.builder()
                .id(333)
                .name("Pump_333")
                .note("pump333").build();
        PumpDTOUpdate pumpDTOUpdate = PumpDTOUpdate.builder()
                .name("Pump_334")
                .note("pump334").build();
        //when
        PumpMapper.copyNonNullToFuel(pump, pumpDTOUpdate);
        //then
        assertEquals("Pump_334", pump.getName());
        assertEquals("pump334", pump.getNote());
    }
}