package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.card.CardDTOCreate;
import com.gasstation.managementsystem.model.dto.card.CardDTOFilter;
import com.gasstation.managementsystem.model.dto.card.CardDTOUpdate;
import com.gasstation.managementsystem.model.mapper.CardMapper;
import com.gasstation.managementsystem.repository.CardRepository;
import com.gasstation.managementsystem.repository.criteria.CardRepositoryCriteria;
import com.gasstation.managementsystem.service.CardService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardRepositoryCriteria cardCriteria;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;

    private HashMap<String, Object> listCardToMap(List<Card> cards) {
        List<CardDTO> cardDTOS = cards.stream().map(CardMapper::toCardDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", cardDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(CardDTOFilter filter) {
        HashMap<String, Object> temp = cardCriteria.findAll(filter);
        HashMap<String, Object> map = listCardToMap((List<Card>) temp.get("data"));
        map.put("totalElement", temp.get("totalElement"));
        map.put("totalPage", temp.get("totalPage"));
        return map;
    }

    @Override
    public CardDTO findById(UUID id) throws CustomNotFoundException {
        return CardMapper.toCardDTO(optionalValidate.getCardById(id));
    }

    @Override
    public CardDTO create(CardDTOCreate cardDTOCreate) throws CustomDuplicateFieldException, CustomNotFoundException {
        checkDuplicate(cardDTOCreate.getDriverPhone());
        Card card = CardMapper.toCard(cardDTOCreate);
        card.setActivatedUser(userHelper.getUserLogin());
        if (cardDTOCreate.getCustomerId() != null) {
            card.setCustomer(optionalValidate.getUserById(cardDTOCreate.getCustomerId()));
        }
        card = cardRepository.save(card);
        return CardMapper.toCardDTO(card);
    }

    private void checkDuplicate(String driverPhone) throws CustomDuplicateFieldException {
        if (driverPhone == null) return;
        Optional<Card> cardOptional = cardRepository.findByDriverPhone(driverPhone);
        if (cardOptional.isPresent()) {
            throw new CustomDuplicateFieldException(CustomError.builder()
                    .code("duplicate").field("phone").message("Phone is duplicate").table("card_table").build());
        }
    }

    @Override
    public CardDTO update(UUID id, CardDTOUpdate cardDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Card oldCard = optionalValidate.getCardById(id);
        String phone = cardDTOUpdate.getDriverPhone();
        if (needCheckDuplicate(phone, oldCard)) {
            checkDuplicate(phone);
        }
        CardMapper.copyNonNullToCard(oldCard, cardDTOUpdate);
        Integer customerId = cardDTOUpdate.getCustomerId();
        if (customerId != null) {
            User customer = optionalValidate.getUserById(customerId);
            oldCard.setCustomer(customer);
        }
        oldCard = cardRepository.save(oldCard);
        return CardMapper.toCardDTO(oldCard);
    }

    private boolean needCheckDuplicate(String phone, Card oldCard) {
        if (phone == null) return false;
        return !phone.equalsIgnoreCase(oldCard.getDriverPhone());
    }


    @Override
    public CardDTO delete(UUID id) throws CustomNotFoundException {
        Card card = optionalValidate.getCardById(id);
        cardRepository.delete(card);
        return CardMapper.toCardDTO(card);
    }
}
