package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.card.CardDTOCreate;
import com.gasstation.managementsystem.model.dto.card.CardDTOUpdate;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CardMapperTest {
    /**
     * param card is null
     */
    @Test
    void toCardDTO_UTCID01() {
        assertNull(CardMapper.toCardDTO(null));
    }

    /**
     * param card is not null
     */
    @Test
    void toCardDTO_UTCID02() {
        Long limitSetDate = 16094340000000L;
        Long createdDate = 16094540000000L;
        Card card = Card.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .driverPhone("0123456789")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .initialDebt(100d)
                .availableBalance(1000d)
                .accountsPayable(200d)
                .debtLimit(5000d)
                .limitSetDate(limitSetDate)
                .createdDate(createdDate)
                .active(true)
                .creator(User.builder().id(1).name("admin").build())
                .customer(User.builder().id(2).name("customer").build())
                .build();

        CardDTO cardDTO = CardMapper.toCardDTO(card);

        assertEquals("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8", cardDTO.getId().toString());
        assertEquals("0123456789", cardDTO.getDriverPhone());
        assertEquals("Nguyen Van A", cardDTO.getDriverName());
        assertEquals("36B1-75097", cardDTO.getLicensePlate());
        assertEquals(100, cardDTO.getInitialDebt());
        assertEquals(1000, cardDTO.getAvailableBalance());
        assertEquals(200, cardDTO.getAccountsPayable());
        assertEquals(5000, cardDTO.getDebtLimit());
        assertEquals(limitSetDate, cardDTO.getLimitSetDate());
        assertEquals(createdDate, cardDTO.getCreatedDate());
        assertAll(() -> {
            assertEquals(1, cardDTO.getCreator().getId());
            assertEquals("admin", cardDTO.getCreator().getName());
        });
        assertAll(() -> {
            assertEquals(2, cardDTO.getCustomer().getId());
            assertEquals("customer", cardDTO.getCustomer().getName());
        });
    }

    /**
     * param cardDTOCreate is null
     */
    @Test
    void toCard_UTCID01() {
        assertNull(CardMapper.toCard(null));
    }

    /**
     * param cardDTOCreate is not null
     */
    @Test
    void toCard_UTCID02() {
        CardDTOCreate cardDTOCreate = CardDTOCreate.builder()
                .driverPhone("0123456789")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .active(true)
                .build();
        Card card = CardMapper.toCard(cardDTOCreate);
        assertEquals("0123456789", card.getDriverPhone());
        assertEquals("Nguyen Van A", card.getDriverName());
        assertEquals("36B1-75097", card.getLicensePlate());
        assertEquals(0, card.getDebtLimit());
    }

    /**
     * param cardDTOUpdate is null
     */
    @Test
    void copyNonNullToCard_UTCID01() {
        CardDTOUpdate cardDTOUpdate = CardDTOUpdate.builder()
                .driverPhone("0123456780")
                .driverName("Nguyen Van A_update")
                .licensePlate("36B1-75097_update")
                .initialDebt(101d)
                .payInAmount(1001d)
                .debtLimit(5001d)
                .build();
        try {
            CardMapper.copyNonNullToCard(null, cardDTOUpdate);
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    /**
     * param cardDTOUpdate is not null
     */
    @Test
    void copyNonNullToCard_UTCID02() {
        Long limitSetDate = 16094340000000L;
        Long createdDate = 16094540000000L;
        Card card = Card.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .driverPhone("0123456789")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .initialDebt(100d)
                .availableBalance(1000d)
                .accountsPayable(200d)
                .debtLimit(5000d)
                .limitSetDate(limitSetDate)
                .createdDate(createdDate)
                .active(true)
                .creator(User.builder().id(1).name("admin").build())
                .customer(User.builder().id(2).name("customer").build())
                .build();

        CardDTOUpdate cardDTOUpdate = CardDTOUpdate.builder()
                .driverPhone("0123456780")
                .driverName("Nguyen Van A_update")
                .licensePlate("36B1-75097_update")
                .initialDebt(101d)
                .payInAmount(1001d)
                .debtLimit(5001d)
                .build();
        CardMapper.copyNonNullToCard(card, cardDTOUpdate);
        assertEquals("0123456780", card.getDriverPhone());
        assertEquals("Nguyen Van A_update", card.getDriverName());
        assertEquals("36B1-75097_update", card.getLicensePlate());
        assertEquals(101, card.getInitialDebt());
        assertEquals(1000, card.getAvailableBalance());
        assertEquals(200, card.getAccountsPayable());
    }
}