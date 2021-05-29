package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.model.dto.CardDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface CardService {
    public HashMap<String, Object> findAll(Pageable pageable);

    public CardDTO findById(int id);

    public CardDTO save(Card card);

    public CardDTO delete(int id);
}
