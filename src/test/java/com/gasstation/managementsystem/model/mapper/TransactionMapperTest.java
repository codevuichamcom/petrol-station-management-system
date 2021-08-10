package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTO;
import com.gasstation.managementsystem.model.dto.transaction.TransactionDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMapperTest extends TransactionMapper {

    /**
     * param transaction is null
     */
    @Test
    void testToTransactionDTO_UTCID01() {
        assertNull(TransactionMapper.toTransactionDTO(null));
    }

    /**
     * param transaction is not null
     */
    @Test
    void testToTransactionDTO_UTCID02() {
        //given
        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        User customer = User.builder()
                .id(1)
                .name("customer").build();

        Card card = Card.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .customer(customer)
                .build();

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

        Transaction transaction = Transaction.builder()
                .id(1)
                .time(time)
                .volume(123d)
                .uuid("123456")
                .card(card)
                .pumpShift(PumpShift.builder()
                        .id(1)
                        .shift(Shift.builder()
                                .id(1)
                                .name("shift")
                                .build())
                        .pump(pump)
                        .build())
                .unitPrice(50500d).build();
        //when
        TransactionDTO transactionDTO = TransactionMapper.toTransactionDTO(transaction);
        //then
        assertEquals(1, transactionDTO.getId());
        assertEquals(1609434000000L, transactionDTO.getTime());
        assertEquals(123, transactionDTO.getVolume());
        assertEquals(50500, transactionDTO.getUnitPrice());
        assertEquals("123456", transactionDTO.getUuid());
        assertAll(() -> {
            CardDTO cardDTO = transactionDTO.getCard();
            assertEquals("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8", cardDTO.getId().toString());

            UserDTO customerDTO = cardDTO.getCustomer();
            assertAll(() -> {
                assertEquals(1, customerDTO.getId());
                assertEquals("customer", customerDTO.getName());
            });

        });
        assertAll(() -> {
            PumpShiftDTO pumpShiftDTO = transactionDTO.getPumpShift();
            assertEquals(1, pumpShiftDTO.getId());
            ShiftDTO shiftDTO = pumpShiftDTO.getShift();
            assertAll(() -> {
                assertEquals(1, shiftDTO.getId());
                assertEquals("shift", shiftDTO.getName());
            });
        });
    }

    /**
     * param TransactionDTOCreate is null
     */
    @Test
    void testToTransaction_UTCID01() {
        assertNull(TransactionMapper.toTransaction(null));
    }

    /**
     * param TransactionDTOCreate is not null
     */
    @Test
    void testToTransaction_UTCID02() {
        //given
        Long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        TransactionDTOCreate transactionDTOCreate = TransactionDTOCreate.builder()
                .time(time)
                .volume(200d)
                .unitPrice(100d)
                .uuid("123456").build();
        //when
        Transaction transaction = TransactionMapper.toTransaction(transactionDTOCreate);
        //then
        assertEquals(time, transaction.getTime());
        assertEquals(0.2, transaction.getVolume());
        assertEquals(100, transaction.getUnitPrice());
        assertEquals("123456", transaction.getUuid());
    }
}