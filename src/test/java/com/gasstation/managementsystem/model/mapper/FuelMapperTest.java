package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Fuel;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOCreate;
import com.gasstation.managementsystem.model.dto.fuel.FuelDTOUpdate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuelMapperTest extends FuelMapper {
    /**
     * param fuel is null
     */
    @Test
    void testToFuelDTO_UTCID01() {
        assertNull(FuelMapper.toFuelDTO(null));
    }

    /**
     * param fuel is not null
     */
    @Test
    void testToFuelDTO_UTCID02() {
        //given
        Fuel fuel = Fuel.builder()
                .id(23)
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();
        //when
        FuelDTO fuelDTO = FuelMapper.toFuelDTO(fuel);
        //then
        assertEquals(23, fuelDTO.getId());
        assertEquals("E92", fuelDTO.getName());
        assertEquals("litter", fuelDTO.getUnit());
        assertEquals(32460, fuelDTO.getPrice());
        assertEquals("TYPE_E92", fuelDTO.getType());
    }

    /**
     * param fuelDTOCreate is null
     */
    @Test
    void testToFuel_UTCID01() {
        assertNull(FuelMapper.toFuel(null));
    }

    /**
     * param fuelDTOCreate is not null
     */
    @Test
    void testToFuel_UTCID02() {
        //given
        FuelDTOCreate fuelDTOCreate = FuelDTOCreate.builder()
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();
        //when
        Fuel fuel = FuelMapper.toFuel(fuelDTOCreate);
        //then
        assertEquals("E92", fuel.getName());
        assertEquals("litter", fuel.getUnit());
        assertEquals(32460, fuel.getPrice());
        assertEquals("TYPE_E92", fuel.getType());
    }

    /**
     * param fuel is null
     */
    @Test
    void testCopyNonNullToFuel_UTCID01() {
        //given
        FuelDTOUpdate fuelDTOUpdate = FuelDTOUpdate.builder()
                .name("E95")
                .unit("lit")
                .price(44460d)
                .type("TYPE_E95").build();
        //when
        try {
            FuelMapper.copyNonNullToFuel(null, fuelDTOUpdate);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * param fuel is null
     */
    @Test
    void testCopyNonNullToFuel_UTCID02() {
        //given
        Fuel fuel = Fuel.builder()
                .id(23)
                .name("E92")
                .unit("litter")
                .price(32460d)
                .type("TYPE_E92").build();

        FuelDTOUpdate fuelDTOUpdate = FuelDTOUpdate.builder()
                .name("E95")
                .unit("lit")
                .price(44460d)
                .type("TYPE_E95").build();
        //when
        FuelMapper.copyNonNullToFuel(fuel, fuelDTOUpdate);
        //then
        assertEquals("E95", fuel.getName());
        assertEquals("lit", fuel.getUnit());
        assertEquals(44460, fuel.getPrice());
        assertEquals("TYPE_E95", fuel.getType());
    }
}