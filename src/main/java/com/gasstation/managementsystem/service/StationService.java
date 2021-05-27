package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.model.dto.StationDTO;

import java.util.List;

public interface StationService {
    public List<StationDTO> findAll();

    public StationDTO findById(int id);

    public StationDTO save(Station station);

    public StationDTO delete(int id);
}
