package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.card.CardDTOCreate;
import com.gasstation.managementsystem.model.dto.card.CardDTOFilter;
import com.gasstation.managementsystem.model.dto.card.CardDTOUpdate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.mapper.CardMapper;
import com.gasstation.managementsystem.repository.CardRepository;
import com.gasstation.managementsystem.repository.criteria.CardRepositoryCriteria;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardServiceImplTest {
    @Mock
    private CardRepository cardRepository;
    @Mock
    private OptionalValidate optionalValidate;
    @Mock
    private UserHelper userHelper;
    @Mock
    private CardRepositoryCriteria cardCriteria;
    @InjectMocks
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * param Pageable
     */
    @Test
    void findAll_UTCID01() {
        List<Card> mockRepository = new ArrayList<>();
        List<CardDTO> mockResult = new ArrayList<>();
        mockData(mockRepository, mockResult);
//        final int PAGE_INDEX = 1;
//        final int PAGE_SIZE = 3;
//        Page<Card> mockRepositoryPaged = new PageImpl<>(mockRepository.subList(PAGE_INDEX - 1, PAGE_SIZE));
//        List<CardDTO> mockResultPaged = mockResult.subList(PAGE_INDEX - 1, PAGE_SIZE);
//        Mockito.when(cardRepository.findAll(PageRequest.of(PAGE_INDEX - 1, PAGE_SIZE))).thenReturn(mockRepositoryPaged);

        CardDTOFilter filter = CardDTOFilter.builder().accountsPayable(200d).build();
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("totalElement", 10);
        temp.put("data", mockRepository);
        temp.put("totalPage",3);
        Mockito.when(cardRepository.findAll()).thenReturn(mockRepository);
        Mockito.when(cardCriteria.findAll(Mockito.any(CardDTOFilter.class))).thenReturn(temp);
        List<CardDTO> listResultService = (List<CardDTO>) cardService.findAll(filter).get("data");
        for (int i = 0; i < listResultService.size(); i++) {
            CardDTO o1 = mockResult.get(i);
            CardDTO o2 = listResultService.get(i);
            assertEquals(o1, o2);
        }
    }

    private void mockData(List<Card> mockRepository, List<CardDTO> mockResult) {
        Long limitSetDate = 16094540000000L;
        Long createdDate = 16094640000000L;
        Long activeDate = 16094540000000L;

        for (int i = 1; i <= 10; i++) {
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
            mockRepository.add(card);
            mockResult.add(CardMapper.toCardDTO(card));
        }
    }

    /**
     * param Pageable
     */
    @Test
    void findAll_UTCID02() {
//        List<Card> mockRepository = new ArrayList<>();
//        List<CardDTO> mockResult = new ArrayList<>();
//        mockData(mockRepository, mockResult);
//
//        Sort sort = Sort.by(Sort.Direction.ASC, "id");
//        Mockito.when(cardRepository.findAll(sort)).thenReturn(mockRepository);
//        List<CardDTO> listResultService = (List<CardDTO>) cardService.findAll().get("data");
//        for (int i = 0; i < listResultService.size(); i++) {
//            CardDTO o1 = mockResult.get(i);
//            CardDTO o2 = listResultService.get(i);
//            assertEquals(o1, o2);
//        }
    }


    @Test
    void findById() throws CustomNotFoundException {
        Long limitSetDate = 16094540000000L;
        Long createdDate = 16094640000000L;
        Long activeDate = 16094540000000L;

        Card mockRepository = Card.builder()
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
        CardDTO mockResult = CardDTO.builder()
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
                .creator(UserDTO.builder().id(1).name("admin").build())
                .customer(UserDTO.builder().id(2).name("customer").build())
                .build();
        Mockito.when(optionalValidate.getCardById(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(mockRepository);
        assertEquals(mockResult, cardService.findById(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")));
    }

    @Test
    void create_UTCID01() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long limitSetDate = 16094540000000L;
        Long createdDate = 16094640000000L;
        Long activeDate = 16094540000000L;

        Card mockRepository = Card.builder()
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
        CardDTO mockResult = CardDTO.builder()
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
                .creator(UserDTO.builder().id(1).name("admin").build())
                .customer(UserDTO.builder().id(2).name("customer").build())
                .build();
        CardDTOCreate cardDTOCreate = CardDTOCreate.builder()
                .driverPhone("0123456789")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .debtLimit(5000d)
                .active(true)
                .customerId(1)
                .build();
        User user = User.builder().id(1).name("USER").build();
        Mockito.when(userHelper.getUserLogin()).thenReturn(user);
        Mockito.when(cardRepository.save(Mockito.any(Card.class))).thenReturn(mockRepository);
        assertEquals(mockResult, cardService.create(cardDTOCreate));
    }

    /**
     * check duplicate
     */
    @Test
    void create_UTCID02() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long limitSetDate = 16094540000000L;
        Long createdDate = 16094640000000L;
        Long activeDate = 16094540000000L;

        Card mockRepository = Card.builder()
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
        CardDTOCreate cardDTOCreate = CardDTOCreate.builder()
                .driverPhone("0123456789")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75097")
                .debtLimit(5000d)
                .active(true)
                .customerId(1)
                .build();
        Mockito.when(cardRepository.save(Mockito.any(Card.class))).thenReturn(mockRepository);
        Optional<Card> cardOptional = Optional.of(mockRepository);
        Mockito.when(cardRepository.findByLicensePlate("36B1-75097")).thenReturn(cardOptional);
        assertThrows(CustomDuplicateFieldException.class, () -> {
            cardService.create(cardDTOCreate);
        });
    }

    @Test
    void update() throws CustomNotFoundException, CustomDuplicateFieldException {
        Long limitSetDate = DateTimeHelper.getCurrentDate();
        Long createdDate = 16094640000000L;
        Long activeDate = 16094540000000L;

        Card mockRepository = Card.builder()
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

        CardDTO mockResult = CardDTO.builder()
                .id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
                .driverPhone("0123456788")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75098")
                .initialDebt(1000d)
                .availableBalance(11000d)
                .accountsPayable(200d)
                .debtLimit(50000d)
                .limitSetDate(limitSetDate)
                .createdDate(createdDate)
                .active(true)
                .creator(UserDTO.builder().id(1).name("admin").build())
                .customer(UserDTO.builder().id(2).name("customer").build())
                .build();

        CardDTOUpdate cardDTOUpdate = CardDTOUpdate.builder()
                .driverPhone("0123456788")
                .driverName("Nguyen Van A")
                .licensePlate("36B1-75098")
                .initialDebt(1000d)
                .payInAmount(10000d)
                .debtLimit(50000d)
                .active(true)
                .build();
        Mockito.when(optionalValidate.getCardById(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(mockRepository);
        Mockito.when(optionalValidate.getUserById(1)).thenReturn(mockRepository.getCreator());
        Mockito.when(optionalValidate.getUserById(2)).thenReturn(mockRepository.getCustomer());
        Mockito.when(cardRepository.save(Mockito.any(Card.class))).thenReturn(mockRepository);
        assertEquals(mockResult, cardService.update(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"), cardDTOUpdate));
    }

    @Test
    void delete() throws CustomNotFoundException {
        Long limitSetDate = 16094540000000L;
        Long createdDate = 16094640000000L;
        Long activeDate = 16094540000000L;

        Card mockRepository = Card.builder()
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

        CardDTO mockResult = CardDTO.builder()
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
                .creator(UserDTO.builder().id(1).name("admin").build())
                .customer(UserDTO.builder().id(2).name("customer").build())
                .build();
        Mockito.when(optionalValidate.getCardById(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(mockRepository);
        assertEquals(mockResult, cardService.delete(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")));
    }
}