package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTOCreate;
import com.gasstation.managementsystem.model.dto.tank.TankDTOUpdate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TankMapperTest extends TankMapper {
    /**
     * param tank is null
     */
    @Test
    void testToTankDTO_UTCID01() {
        assertNull(TankMapper.toTankDTO(null));
    }

    /**
     * param tank is not null
     */
    @Test
    void testToTankDTO_UTCID02() {
        //given
        Tank tank = Tank.builder()
                .id(100)
                .name("tank_100")
                .volume(900d)
                .remain(20d)
                .currentPrice(505500d).build();
        //when
        TankDTO tankDTO = TankMapper.toTankDTO(tank);
        //then
        assertEquals(100, tankDTO.getId());
        assertEquals("tank_100", tankDTO.getName());
        assertEquals(900, tankDTO.getVolume());
        assertEquals(20, tankDTO.getRemain());
        assertEquals(505500, tankDTO.getCurrentPrice());
    }

    /**
     * param tankDTO is null
     */
    @Test
    void testToTank_UTCID01() {
        assertNull(TankMapper.toTank(null));
    }

    /**
     * param tankDTO is not null
     */
    @Test
    void testToTank_UTCID02() {
        //given
        TankDTOCreate tankDTOCreate = TankDTOCreate.builder()
                .name("tank_100")
                .volume(900d)
                .currentPrice(505500d).build();
        //when
        Tank tank = TankMapper.toTank(tankDTOCreate);
        //then
        assertEquals("tank_100", tank.getName());
        assertEquals(900, tank.getVolume());
        assertEquals(0, tank.getRemain());
        assertEquals(505500, tank.getCurrentPrice());
    }

    /**
     * precondition tank is null
     */
    @Test
    void testCopyNonNullToTank_UTCID01() {
        //given
        TankDTOUpdate tankDTOUpdate = TankDTOUpdate.builder()
                .name("tank_100")
                .volume(900d)
                .remain(100d)
                .fuelId(1)
                .stationId(1)
                .currentPrice(505500d).build();
        //then
        try {
            TankMapper.copyNonNullToTank(null, tankDTOUpdate);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * precondition tank is not null
     */
    @Test
    void testCopyNonNullToTank_UTCID02() {
        //given
        Tank tank = Tank.builder()
                .id(100)
                .name("tank_100")
                .volume(900d)
                .remain(20d)
                .currentPrice(505500d).build();

        TankDTOUpdate tankDTOUpdate = TankDTOUpdate.builder()
                .name("tank_111")
                .volume(700d)
                .remain(200d)
                .currentPrice(606600d).build();
        //when
        TankMapper.copyNonNullToTank(tank, tankDTOUpdate);
        //then
        assertEquals(100, tank.getId());
        assertEquals("tank_111", tank.getName());
        assertEquals(700, tank.getVolume());
        assertEquals(200, tank.getRemain());
        assertEquals(606600d, tank.getCurrentPrice());
    }
}