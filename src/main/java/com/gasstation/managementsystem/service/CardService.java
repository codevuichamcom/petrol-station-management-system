package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.card.CardDTOCreate;
import com.gasstation.managementsystem.model.dto.card.CardDTOUpdate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public interface CardService {
    HashMap<String, Object> findAll(Pageable pageable);

    HashMap<String, Object> findAll(Sort sort);

    CardDTO findById(int id) throws CustomNotFoundException;

    CardDTO create(CardDTOCreate cardDTOCreate) throws CustomDuplicateFieldException, CustomNotFoundException;

    CardDTO update(int id, CardDTOUpdate cardDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException;

    CardDTO delete(int id) throws CustomNotFoundException;
}
