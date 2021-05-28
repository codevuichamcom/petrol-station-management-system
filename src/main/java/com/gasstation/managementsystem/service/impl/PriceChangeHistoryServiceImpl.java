package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import com.gasstation.managementsystem.model.dto.PriceChangeHistoryDTO;
import com.gasstation.managementsystem.repository.PriceChangeHistoryRepository;
import com.gasstation.managementsystem.service.PriceChangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceChangeHistoryServiceImpl implements PriceChangeHistoryService {
    @Autowired
    PriceChangeHistoryRepository priceChangeHistoryRepository;

    @Override
    public List<PriceChangeHistoryDTO> findAll() {
        List<PriceChangeHistory> priceChangeHistories = priceChangeHistoryRepository.findAll();
        List<PriceChangeHistoryDTO> priceChangeHistoryDTOS = new ArrayList<>();
        for (PriceChangeHistory priceChangeHistory : priceChangeHistories) {
            priceChangeHistoryDTOS.add(new PriceChangeHistoryDTO(priceChangeHistory));
        }
        return priceChangeHistoryDTOS;
    }

    @Override
    public PriceChangeHistoryDTO findById(int id) {
        return new PriceChangeHistoryDTO(priceChangeHistoryRepository.findById(id).get());
    }

    @Override
    public PriceChangeHistoryDTO save(PriceChangeHistory priceChangeHistory) {
        priceChangeHistoryRepository.save(priceChangeHistory);
        return new PriceChangeHistoryDTO(priceChangeHistory);
    }

    @Override
    public PriceChangeHistoryDTO delete(int id) {
        PriceChangeHistory priceChangeHistory = priceChangeHistoryRepository.findById(id).get();
        if (priceChangeHistory != null) {
            priceChangeHistoryRepository.delete(priceChangeHistory);
            return new PriceChangeHistoryDTO(priceChangeHistory);
        }
        return null;
    }
}
