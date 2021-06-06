package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.StationDTO;
import com.gasstation.managementsystem.repository.AccountRepository;
import com.gasstation.managementsystem.repository.StationRepository;
import com.gasstation.managementsystem.repository.UserRepository;
import com.gasstation.managementsystem.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private HashMap<String,Object> listStationToMap(List<Station> stations){
        List<StationDTO> stationDTOS = new ArrayList<>();
        for (Station station : stations) {
            stationDTOS.add(new StationDTO(station));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", stationDTOS);
        return map;
    }
    @Override
    public HashMap<String, Object> findAll(Pageable pageable,Principal principal) {
        Account account =accountRepository.findByUsername(principal.getName());
        Page<Station> stations = stationRepository.findByOwnerId(account.getUserInfo().getId(),pageable);
        HashMap<String, Object> map = listStationToMap(stations.getContent());
        map.put("totalElement", stations.getTotalElements());
        map.put("totalPage", stations.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Principal principal) {
        Account account = accountRepository.findByUsername(principal.getName());
        List<Station> stations = new ArrayList<>();
        int userTypeId = account.getUserInfo().getUserType().getId();
        switch (userTypeId) {
            case UserType.ADMIN:
                stations = stationRepository.findAll();
                break;
            case UserType.OWNER:
                stations = stationRepository.findByOwnerId(account.getUserInfo().getId());
                break;
        }
        return listStationToMap(stations);
    }

    @Override
    public StationDTO findById(int id) {
        return new StationDTO(stationRepository.findById(id).get());
    }

    @Override
    public StationDTO save(Station station) {
        User owner = userRepository.getById(station.getOwner().getId());
        station.setOwner(owner);
        station = stationRepository.save(station);
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
