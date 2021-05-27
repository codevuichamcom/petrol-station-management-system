package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.model.dto.CardDTO;
import com.gasstation.managementsystem.repository.CardRepository;
import com.gasstation.managementsystem.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Override
    public List<CardDTO> findAll() {
        List<Card> cards = cardRepository.findAll();
        List<CardDTO> cardDTOS = new ArrayList<>();
        for (Card card : cards) {
            cardDTOS.add(new CardDTO(card));
        }
        return cardDTOS;
    }

    @Override
    public CardDTO findById(int id) {
        return new CardDTO(cardRepository.findById(id).get());
    }

    @Override
    public CardDTO save(Card card) {
        cardRepository.save(card);
        return new CardDTO(card);
    }

    @Override
    public CardDTO delete(int id) {
        Card card = cardRepository.findById(id).get();
        if (card != null) {
            cardRepository.delete(card);
            return new CardDTO(card);
        }
        return null;
    }
}
