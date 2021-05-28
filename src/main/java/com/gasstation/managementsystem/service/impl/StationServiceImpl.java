package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.model.dto.StationDTO;
import com.gasstation.managementsystem.repository.StationRepository;
import com.gasstation.managementsystem.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    StationRepository stationRepository;

    @Override
    public List<StationDTO> findAll() {
        List<Station> stations = stationRepository.findAll();
        List<StationDTO> stationDTOS = new ArrayList<>();
        for (Station station : stations) {
            stationDTOS.add(new StationDTO(station));
        }
        return stationDTOS;
    }

    @Override
    public StationDTO findById(int id) {
        return new StationDTO(stationRepository.findById(id).get());
    }

    @Override
    public StationDTO save(Station station) {
        stationRepository.save(station);
        return new StationDTO(station);
    }

    @Override
    public StationDTO delete(int id) {
        Station station = stationRepository.findById(id).get();
        if (station != null) {
            stationRepository.delete(station);
            return new StationDTO(station);
        }
        return null;
    }
}
