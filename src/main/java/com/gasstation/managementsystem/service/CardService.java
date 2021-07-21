package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.card.CardDTO;
import com.gasstation.managementsystem.model.dto.card.CardDTOCreate;
import com.gasstation.managementsystem.model.dto.card.CardDTOUpdate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.UUID;

public interface CardService {
    HashMap<String, Object> findAll(Pageable pageable);

    HashMap<String, Object> findAll(Sort sort);

    CardDTO findById(UUID id) throws CustomNotFoundException;

    CardDTO create(CardDTOCreate cardDTOCreate) throws CustomDuplicateFieldException, CustomNotFoundException;

    CardDTO update(UUID id, CardDTOUpdate cardDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException;

    CardDTO delete(UUID id) throws CustomNotFoundException;
}
