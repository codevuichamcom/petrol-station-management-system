package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import com.gasstation.managementsystem.model.dto.PriceChangeHistoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface PriceChangeHistoryService {
    public HashMap<String,Object> findAll(Pageable pageable);

    public PriceChangeHistoryDTO findById(int id);

    public PriceChangeHistoryDTO save(PriceChangeHistory priceChangeHistory);

    public PriceChangeHistoryDTO delete(int id);
}
