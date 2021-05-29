package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.model.dto.CardDTO;
import com.gasstation.managementsystem.model.dto.CardDTO;
import com.gasstation.managementsystem.repository.CardRepository;
import com.gasstation.managementsystem.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Card> card = cardRepository.findAll(pageable);
        List<CardDTO> supplierDTOS = new ArrayList<>();
        for (Card supplier : card) {
            supplierDTOS.add(new CardDTO(supplier));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", supplierDTOS);
        map.put("totalElement", card.getTotalElements());
        map.put("totalPage", card.getTotalPages());
        return map;
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
