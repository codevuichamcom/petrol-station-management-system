package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
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
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
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
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        if (userType.getId() == UserType.CUSTOMER) {
            filter.setCustomerId(userLoggedIn.getId());
        }
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
        checkDuplicate(cardDTOCreate.getLicensePlate());
        Card card = CardMapper.toCard(cardDTOCreate);
        card.setCreator(userHelper.getUserLogin());
        if (cardDTOCreate.getCustomerId() != null) {
            card.setCustomer(optionalValidate.getUserById(cardDTOCreate.getCustomerId()));
        }
        trimString(card);
        card = cardRepository.save(card);
        return CardMapper.toCardDTO(card);
    }

    private void trimString(Card card) {
        card.setDriverName(StringUtils.trim(card.getDriverName()));
        card.setLicensePlate(StringUtils.trim(card.getLicensePlate()));
    }

    private void checkDuplicate(String licensePalate) throws CustomDuplicateFieldException {
        licensePalate = StringUtils.trim(licensePalate);
        if (licensePalate == null) return;
        Optional<Card> cardOptional = cardRepository.findByLicensePlate(licensePalate);
        if (cardOptional.isPresent()) {
            throw new CustomDuplicateFieldException(CustomError.builder()
                    .code("duplicate").field("licensePlate").message("License Plate is duplicate").table("card_table").build());
        }
    }

    @Override
    public CardDTO update(UUID id, CardDTOUpdate cardDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Card oldCard = optionalValidate.getCardById(id);
        String licensePalate = StringUtils.trim(cardDTOUpdate.getLicensePlate());
        if (needCheckDuplicate(licensePalate, oldCard)) {
            checkDuplicate(licensePalate);
        }
        CardMapper.copyNonNullToCard(oldCard, cardDTOUpdate);
        if (cardDTOUpdate.getDebtLimit() != null) {
            oldCard.setLimitSetDate(DateTimeHelper.getCurrentDate());
        }
        if (cardDTOUpdate.getPayInAmount() != null) {
            oldCard.setAvailableBalance(oldCard.getAvailableBalance() + cardDTOUpdate.getPayInAmount());
        }
        Integer customerId = cardDTOUpdate.getCustomerId();
        if (customerId != null && oldCard.getCustomer() == null) {
            oldCard.setCustomer(optionalValidate.getUserById(customerId));
        }
        trimString(oldCard);
        oldCard = cardRepository.save(oldCard);
        return CardMapper.toCardDTO(oldCard);
    }

    private boolean needCheckDuplicate(String licensePlate, Card oldCard) {
        licensePlate = StringUtils.trim(licensePlate);
        if (licensePlate == null) return false;
        return !licensePlate.equalsIgnoreCase(oldCard.getLicensePlate());
    }


    @Override
    public CardDTO delete(UUID id) throws CustomNotFoundException, CustomBadRequestException {
        Card card = optionalValidate.getCardById(id);
        try {
            cardRepository.delete(card);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("fk_constraint")
                    .field("card_id")
                    .message("Card in use").build());
        }
        return CardMapper.toCardDTO(card);
    }
}
