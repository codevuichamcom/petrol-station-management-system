package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import com.gasstation.managementsystem.model.dto.PriceChangeHistoryDTO;
import com.gasstation.managementsystem.repository.PriceChangeHistoryRepository;
import com.gasstation.managementsystem.service.PriceChangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PriceChangeHistoryServiceImpl implements PriceChangeHistoryService {
    @Autowired
    PriceChangeHistoryRepository priceChangeHistoryRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<PriceChangeHistory> priceChangeHistories = priceChangeHistoryRepository.findAll(pageable);
        List<PriceChangeHistoryDTO> priceChangeHistoryDTOS = new ArrayList<>();
        for (PriceChangeHistory priceChangeHistory : priceChangeHistories) {
            priceChangeHistoryDTOS.add(new PriceChangeHistoryDTO(priceChangeHistory));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", priceChangeHistoryDTOS);
        map.put("totalElement", priceChangeHistories.getTotalElements());
        map.put("totalPage", priceChangeHistories.getTotalPages());
        return map;
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
