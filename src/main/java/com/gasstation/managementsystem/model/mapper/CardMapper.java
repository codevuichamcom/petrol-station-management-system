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
        User activateUser = card.getActivateUser();
        User customer = card.getCustomer();
        UserDTO activateUserDTO = activateUser != null ? UserDTO.builder().id(activateUser.getId()).name(activateUser.getName()).build() : null;
        UserDTO customerDTO = customer != null ? UserDTO.builder().id(customer.getId()).name(customer.getName()).build() : null;
        return CardDTO.builder()
                .id(card.getId())
                .driverPhone(card.getDriverPhone())
                .driverName(card.getDriverName())
                .licensePalates(card.getLicensePalates())
                .initialDebt(card.getInitialDebt())
                .availableBalance(card.getAvailableBalance())
                .outstandingBalance(card.getOutstandingBalance())
                .debtLimit(card.getDebtLimit())
                .limitSetDate(DateTimeHelper.formatDate(card.getLimitSetDate(), "yyyy-MM-dd"))
                .issuedDate(DateTimeHelper.formatDate(card.getIssuedDate(), "yyyy-MM-dd"))
                .activeDate(DateTimeHelper.formatDate(card.getActiveDate(), "yyyy-MM-dd"))
                .activateUser(activateUserDTO)
                .customer(customerDTO)
                .build();
    }

    public static Card toCard(CardDTOCreate cardDTOCreate) {
        if (cardDTOCreate == null) return null;
        return Card.builder()
                .driverPhone(cardDTOCreate.getDriverPhone())
                .driverName(cardDTOCreate.getDriverName())
                .licensePalates(cardDTOCreate.getLicensePalates())
                .initialDebt(cardDTOCreate.getInitialDebt())
                .availableBalance(cardDTOCreate.getAvailableBalance())
                .outstandingBalance(cardDTOCreate.getOutstandingBalance())
                .debtLimit(cardDTOCreate.getDebtLimit())
                .limitSetDate(cardDTOCreate.getLimitSetDate())
                .issuedDate(cardDTOCreate.getIssuedDate())
                .activeDate(cardDTOCreate.getActiveDate())
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
