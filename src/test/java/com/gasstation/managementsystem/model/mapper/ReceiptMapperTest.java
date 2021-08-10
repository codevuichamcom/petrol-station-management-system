//package com.gasstation.managementsystem.model.mapper;
//
//import com.gasstation.managementsystem.entity.*;
//import com.gasstation.managementsystem.model.dto.card.CardDTO;
//import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
//import com.gasstation.managementsystem.model.dto.fuel.FuelDTO;
//import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
//import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;
//import com.gasstation.managementsystem.model.dto.station.StationDTO;
//import com.gasstation.managementsystem.model.dto.tank.TankDTO;
//import com.gasstation.managementsystem.model.dto.user.UserDTO;
//import com.gasstation.managementsystem.utils.DateTimeHelper;
//import org.junit.jupiter.api.Test;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ReceiptMapperTest {
//    /**
//     * param receipt is null
//     */
//    @Test
//    void toReceiptDTO_UTCID01() {
//        assertNull(ReceiptMapper.toReceiptDTO(null));
//    }
//
//    /**
//     * param receipt is not null
//     */
//    @Test
//    void toReceiptDTO_UTCID02() {
//        //given
//        long time = DateTimeHelper.toUnixTime("2021-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
//        User customer = User.builder()
//                .id(1)
//                .name("customer").build();
//        Card card = Card.builder()
//                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .customer(customer)
//                .availableBalance(100d)
//                .accountsPayable(20d)
//                .build();
//        Station station = Station.builder()
//                .id(1)
//                .name("Hoang Long")
//                .address("Me Linh- Ha Noi")
//                .longitude(39d)
//                .latitude(46d)
//                .build();
//        Fuel fuel = Fuel.builder()
//                .id(23)
//                .name("E92")
//                .unit("litter")
//                .price(32460d)
//                .type("TYPE_E92").build();
//        StationDTO stationDTO = StationDTO.builder()
//                .id(1)
//                .name("Hoang Long")
//                .address("Me Linh- Ha Noi")
//                .longitude(39d)
//                .latitude(46d)
//                .build();
//        FuelDTO fuelDTO = FuelDTO.builder()
//                .id(23)
//                .name("E92")
//                .unit("litter")
//                .price(32460d)
//                .type("TYPE_E92").build();
//        Tank tank = Tank.builder()
//                .id(100)
//                .name("tank_100")
//                .volume(900d)
//                .remain(20d)
//                .currentPrice(505500d)
//                .station(station)
//                .fuel(fuel).build();
//        TankDTO tankDTO = TankDTO.builder()
//                .id(100)
//                .name("tank_100")
//                .volume(900d)
//                .remain(20d)
//                .currentPrice(505500d)
//                .station(stationDTO)
//                .fuel(fuelDTO).build();
//        Pump pump = Pump.builder()
//                .id(333)
//                .name("Pump_333")
//                .tank(tank)
//                .note("pump333").build();
//        PumpShift pumpShift = PumpShift.builder()
//                .id(1)
//                .shift(Shift.builder()
//                        .id(1)
//                        .name("shift")
//                        .build())
//                .pump(pump)
//                .build();
//        Transaction transaction = Transaction.builder()
//                .id(1)
//                .time(time)
//                .volume(123d)
//                .uuid("123456")
//                .card(card)
//                .pumpShift(pumpShift)
//                .unitPrice(50500d).build();
//
//        Long createdDate = 16094340000000L;
//        Receipt receipt = Receipt.builder().id(1)
//                .card(Card.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                .createdDate(createdDate)
//                .amount(100d)
//                .creator(User.builder().id(1).build())
//                .reason("reason")
//                .debt(Debt.builder().id(1).accountsPayable(100d)
//                        .transaction(transaction)
//                        .build())
//                .build();
//        ReceiptDTO receiptDTO = ReceiptMapper.toReceiptDTO(receipt);
//        assertEquals(receipt.getId(), receiptDTO.getId());
//        assertEquals(receipt.getCreatedDate(), receiptDTO.getCreatedDate());
//
//        assertEquals(receipt.getAmount(), receiptDTO.getAmount());
//        assertAll(() -> {
//            CardDTO cardDTO = receiptDTO.getCard();
//            assertEquals(receipt.getCard().getId(), cardDTO.getId());
//        });
//        assertAll(() -> {
//            UserDTO creator = receiptDTO.getCreator();
//            assertEquals(receipt.getCreator().getId(), creator.getId());
//        });
//        assertAll(() -> {
//            DebtDTO debtDTO = receiptDTO.getDebt();
//            assertEquals(receipt.getDebt().getId(), debtDTO.getId());
//        });
//    }
//
//    /**
//     * param receiptDTOCreate is null
//     */
//    @Test
//    void toReceipt_UTCID01() {
//        assertNull(ReceiptMapper.toReceipt(null));
//    }
//
//    /**
//     * param receiptDTOCreate is not null
//     */
//    @Test
//    void toReceipt_UTCID02() {
//        Long createdDate = 16094340000000L;
//        ReceiptDTOCreate receiptDTOCreate = ReceiptDTOCreate.builder()
//                .cardId(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .amount(100d)
//                .reason("reason")
//                .build();
//        Receipt receipt = ReceiptMapper.toReceipt(receiptDTOCreate);
//        assertEquals(receipt.getCreatedDate(), DateTimeHelper.getCurrentDate());
//        assertEquals(receipt.getReason(), receiptDTOCreate.getReason());
//        assertEquals(receipt.getAmount(), receiptDTOCreate.getAmount());
//    }
//}