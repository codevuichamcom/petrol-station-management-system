package com.gasstation.managementsystem.model.mapper;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.card.CardDTOCreate;
import com.gasstation.managementsystem.model.dto.card.CardDTOUpdate;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;

public class CardMapper {

    public static CardDTO toCardDTO(Card card) {
        if (card == null) return null;
        User activateUser = card.getActivatedUser();
        User customer = card.getCustomer();
        UserDTO activateUserDTO = activateUser != null ? UserDTO.builder().id(activateUser.getId()).name(activateUser.getName()).build() : null;
        UserDTO customerDTO = customer != null ? UserDTO.builder().id(customer.getId()).name(customer.getName()).build() : null;
        return CardDTO.builder()
                .id(card.getId())
                .driverPhone(card.getDriverPhone())
                .driverName(card.getDriverName())
                .licensePlate(card.getLicensePlate())
                .initialDebt(card.getInitialDebt())
                .availableBalance(card.getAvailableBalance())
                .accountsPayable(card.getAccountsPayable())
                .debtLimit(card.getDebtLimit())
                .limitSetDate(card.getLimitSetDate())
                .createdDate(card.getCreatedDate())
                .active(card.getActive())
                .activateUser(activateUserDTO)
                .customer(customerDTO)
                .build();
    }

    public static Card toCard(CardDTOCreate cardDTOCreate) {
        if (cardDTOCreate == null) return null;
        Boolean active = cardDTOCreate.getActive();
        if (active == null) active = false;
        return Card.builder()
                .driverPhone(cardDTOCreate.getDriverPhone())
                .driverName(cardDTOCreate.getDriverName())
                .licensePlate(cardDTOCreate.getLicensePlate())
                .initialDebt(0d)
                .availableBalance(cardDTOCreate.getAvailableBalance())
                .accountsPayable(0d)
                .debtLimit(cardDTOCreate.getDebtLimit())
                .limitSetDate(DateTimeHelper.getCurrentDate())
                .createdDate(DateTimeHelper.getCurrentDate())
                .active(active)
                .build();
    }

    public static void copyNonNullToCard(Card card, CardDTOUpdate cardDTOUpdate) {
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(card, cardDTOUpdate);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
