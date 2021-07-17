package com.gasstation.managementsystem.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;

public interface PriceChangeHistoryService {
    //    HashMap<String, Object> findAll(Pageable pageable);
    HashMap<String, Object> findAllByTankId(int tankId, Pageable pageable);

//    PriceChangeHistoryDTO findById(int id);
//
//    PriceChangeHistoryDTO save(PriceChangeHistory priceChangeHistory);
//
//    PriceChangeHistoryDTO delete(int id);
}
