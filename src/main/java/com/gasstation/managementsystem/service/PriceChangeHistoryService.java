package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import com.gasstation.managementsystem.model.dto.PriceChangeHistoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface PriceChangeHistoryService {
    HashMap<String, Object> findAll(Pageable pageable);

    PriceChangeHistoryDTO findById(int id);

    PriceChangeHistoryDTO save(PriceChangeHistory priceChangeHistory);

    PriceChangeHistoryDTO delete(int id);
}
