package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.model.dto.CardDTO;

import java.util.List;

public interface CardService {
    public List<CardDTO> findAll();

    public CardDTO findById(int id);

    public CardDTO save(Card card);

    public CardDTO delete(int id);
}
