package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.model.dto.StationDTO;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface StationService {
    public HashMap<String,Object> findAll(Pageable pageable);

    public StationDTO findById(int id);

    public StationDTO save(Station station);

    public StationDTO delete(int id);
}
