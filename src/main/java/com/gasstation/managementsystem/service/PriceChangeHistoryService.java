package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import com.gasstation.managementsystem.model.dto.PriceChangeHistoryDTO;

import java.util.List;

public interface PriceChangeHistoryService {
    public List<PriceChangeHistoryDTO> findAll();

    public PriceChangeHistoryDTO findById(int id);

    public PriceChangeHistoryDTO save(PriceChangeHistory priceChangeHistory);

    public PriceChangeHistoryDTO delete(int id);
}
