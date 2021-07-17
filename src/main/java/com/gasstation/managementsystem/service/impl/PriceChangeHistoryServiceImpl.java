package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.PriceChangeHistory;
import com.gasstation.managementsystem.model.dto.priceChangeHistory.PriceChangeHistoryDTO;
import com.gasstation.managementsystem.model.mapper.PriceChangeHistoryMapper;
import com.gasstation.managementsystem.repository.PriceChangeHistoryRepository;
import com.gasstation.managementsystem.service.PriceChangeHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceChangeHistoryServiceImpl implements PriceChangeHistoryService {
    private final PriceChangeHistoryRepository priceChangeHistoryRepository;

    private HashMap<String, Object> listPriceChangeHistoryToMap(List<PriceChangeHistory> priceChangeHistories) {
        List<PriceChangeHistoryDTO> priceChangeDTOS = priceChangeHistories.stream().map(PriceChangeHistoryMapper::toPriceChangeHistoryDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", priceChangeDTOS);
        return map;
    }

//    @Override
//    public HashMap<String, Object> findAll(Pageable pageable) {
//        Page<PriceChangeHistory> priceChangeHistories = priceChangeHistoryRepository.findAll(pageable);
//        HashMap<String, Object> map = listPriceChangeHistoryToMap(priceChangeHistories.getContent());
//        map.put("totalElement", priceChangeHistories.getTotalElements());
//        map.put("totalPage", priceChangeHistories.getTotalPages());
//        return map;
//    }

    @Override
    public HashMap<String, Object> findAllByTankId(int tankId, Pageable pageable) {
        Page<PriceChangeHistory> priceChangeHistories = priceChangeHistoryRepository.findAllByTankId(tankId, pageable);
        HashMap<String, Object> map = listPriceChangeHistoryToMap(priceChangeHistories.getContent());
        map.put("totalElement", priceChangeHistories.getTotalElements());
        map.put("totalPage", priceChangeHistories.getTotalPages());
        return map;
    }

//    @Override
//    public PriceChangeHistoryDTO findById(int id) {
//        return new PriceChangeHistoryDTO(priceChangeHistoryRepository.findById(id).get());
//    }
//
//    @Override
//    public PriceChangeHistoryDTO save(PriceChangeHistory priceChangeHistory) {
//        priceChangeHistoryRepository.save(priceChangeHistory);
//        return new PriceChangeHistoryDTO(priceChangeHistory);
//    }
//
//    @Override
//    public PriceChangeHistoryDTO delete(int id) {
//        PriceChangeHistory priceChangeHistory = priceChangeHistoryRepository.findById(id).get();
//        if (priceChangeHistory != null) {
//            priceChangeHistoryRepository.delete(priceChangeHistory);
//            return new PriceChangeHistoryDTO(priceChangeHistory);
//        }
//        return null;
//    }
}
