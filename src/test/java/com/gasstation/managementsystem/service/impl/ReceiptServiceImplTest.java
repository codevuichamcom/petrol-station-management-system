//package com.gasstation.managementsystem.service.impl;
//
//import com.gasstation.managementsystem.entity.Card;
//import com.gasstation.managementsystem.entity.Debt;
//import com.gasstation.managementsystem.entity.Receipt;
//import com.gasstation.managementsystem.entity.User;
//import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
//import com.gasstation.managementsystem.model.dto.card.CardDTO;
//import com.gasstation.managementsystem.model.dto.debt.DebtDTO;
//import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTO;
//import com.gasstation.managementsystem.model.dto.receipt.ReceiptDTOCreate;
//import com.gasstation.managementsystem.model.dto.user.UserDTO;
//import com.gasstation.managementsystem.model.mapper.ReceiptMapper;
//import com.gasstation.managementsystem.repository.DebtRepository;
//import com.gasstation.managementsystem.repository.ReceiptRepository;
//import com.gasstation.managementsystem.utils.OptionalValidate;
//import com.gasstation.managementsystem.utils.UserHelper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.Sort;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class ReceiptServiceImplTest {
//    @Mock
//    private ReceiptRepository receiptRepository;
//
//    @Mock
//    private OptionalValidate optionalValidate;
//
//    @Mock
//    private DebtRepository debtRepository;
//
//    @Mock
//    private UserHelper userHelper;
//
//    @InjectMocks
//    private ReceiptServiceImpl receiptService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void findAll() {
//        List<Receipt> mockRepository = new ArrayList<>();
//        List<ReceiptDTO> mockResult = new ArrayList<>();
//        mockData(mockRepository, mockResult);
//
//        Mockito.when(receiptRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"))).thenReturn(mockRepository);
//        List<ReceiptDTO> listResultService = (List<ReceiptDTO>) receiptService.findAll().get("data");
//        for (int i = 0; i < listResultService.size(); i++) {
//            ReceiptDTO o1 = mockResult.get(i);
//            ReceiptDTO o2 = listResultService.get(i);
//            assertEquals(o1, o2);
//        }
//    }
//
//    private void mockData(List<Receipt> mockRepository, List<ReceiptDTO> mockResult) {
//        Long createdDate = 16094340000000L;
//        for (int i = 1; i <= 10; i++) {
//            Receipt receipt = Receipt.builder().id(1)
//                    .card(Card.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                    .createdDate(createdDate)
//                    .amount(100d)
//                    .creator(User.builder().id(1).build())
//                    .reason("reason")
//                    .debt(Debt.builder().id(1)
//                            .build())
//                    .build();
//            mockRepository.add(receipt);
//            mockResult.add(ReceiptMapper.toReceiptDTO(receipt));
//        }
//    }
//
//    @Test
//    void findById() throws CustomNotFoundException {
//        Long createdDate = 16094340000000L;
//        Receipt receipt = Receipt.builder().id(1)
//                .card(Card.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                .createdDate(createdDate)
//                .amount(100d)
//                .creator(User.builder().id(1).build())
//                .reason("reason")
//                .debt(Debt.builder().id(1)
//                        .build())
//                .build();
//        Mockito.when(optionalValidate.getReceiptById(1)).thenReturn(receipt);
//        ReceiptDTO mockResult = ReceiptDTO.builder().id(1)
//                .card(CardDTO.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                .createdDate(createdDate)
//                .amount(100d)
//                .creator(UserDTO.builder().id(1).build())
//                .reason("reason")
//                .debt(DebtDTO.builder().id(1)
//                        .build())
//                .build();
//
//        assertEquals(mockResult, receiptService.findById(1));
//    }
//
//    @Test
//    void create() throws CustomNotFoundException {
//        Long createdDate = 16094340000000L;
//        Card card = Card.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build();
//        Debt debt = Debt.builder().id(1).accountsPayable(1000d).build();
//        User user = User.builder().id(1).build();
//        Receipt receipt = Receipt.builder().id(1)
//                .card(Card.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                .createdDate(createdDate)
//                .amount(100d)
//                .creator(User.builder().id(1).build())
//                .reason("reason")
//                .debt(debt)
//                .build();
//        ReceiptDTO mockResult = ReceiptDTO.builder().id(1)
//                .card(CardDTO.builder().id(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8")).build())
//                .createdDate(createdDate)
//                .amount(100d)
//                .creator(UserDTO.builder().id(1).build())
//                .reason("reason")
//                .debt(DebtDTO.builder().id(1)
//                        .build())
//                .build();
//        ReceiptDTOCreate receiptDTOCreate = ReceiptDTOCreate.builder()
//                .cardId(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))
//                .amount(100d)
//                .reason("reason")
//                .debtId(1)
//                .build();
//        Mockito.when(userHelper.getUserLogin()).thenReturn(user);
//        Mockito.when(optionalValidate.getCardById(UUID.fromString("d9ae22ee-3153-4e2b-9f9d-86ee38d5a7c8"))).thenReturn(card);
//        Mockito.when(optionalValidate.getDebtById(1)).thenReturn(debt);
//        Mockito.when(receiptRepository.save(Mockito.any(Receipt.class))).thenReturn(receipt);
//        Mockito.when(debtRepository.save(Mockito.any(Debt.class))).thenReturn(debt);
//        assertEquals(mockResult, receiptService.create(receiptDTOCreate));
//    }
//}