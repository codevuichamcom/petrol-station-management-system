package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.station.StationDTO;
import com.gasstation.managementsystem.model.dto.station.StationDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdate;
import com.gasstation.managementsystem.model.mapper.StationMapper;
import com.gasstation.managementsystem.repository.StationRepository;
import com.gasstation.managementsystem.repository.UserRepository;
import com.gasstation.managementsystem.service.StationService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;
    private final UserRepository userRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listStationToMap(List<Station> stations) {
        List<StationDTO> stationDTOS = new ArrayList<>();
        for (Station station : stations) {
            stationDTOS.add(StationMapper.toStationDTO(station));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", stationDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Page<Station> stations = stationRepository.findByOwnerId(user.getId(), pageable);
        HashMap<String, Object> map = listStationToMap(stations.getContent());
        map.put("totalElement", stations.getTotalElements());
        map.put("totalPage", stations.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Station> stations = new ArrayList<>();
        int userTypeId = user.getUserType().getId();
        switch (userTypeId) {
            case UserType.ADMIN:
                stations = stationRepository.findAll();
                break;
            case UserType.OWNER:
                stations = stationRepository.findByOwnerId(user.getId());
                break;
        }
        return listStationToMap(stations);
    }

    @Override
    public StationDTO findById(int id) throws CustomNotFoundException {
        return StationMapper.toStationDTO(optionalValidate.getStationById(id));
    }

    private User validateOwner(int id) throws CustomBadRequestException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new CustomBadRequestException("owner is not exist", "id", "user_table");
        }
        User owner = optionalUser.get();
        if (owner.getUserType().getId() != UserType.OWNER) {
            throw new CustomBadRequestException("user not type owner", "id", "user_table");
        }
        return optionalUser.get();
    }

    @Override
    public StationDTO create(StationDTOCreate stationDTOCreate) throws CustomBadRequestException {
        Station station = StationMapper.toStation(stationDTOCreate);
        User owner = validateOwner(stationDTOCreate.getOwnerId());
        station.setOwner(owner);
        station = stationRepository.save(station);
        return StationMapper.toStationDTO(station);
    }

    @Override
    public StationDTO update(int id, StationDTOUpdate stationDTOUpdate) throws CustomBadRequestException, CustomNotFoundException {
        Station station = optionalValidate.getStationById(id);
        StationMapper.copyNonNullToStation(station, stationDTOUpdate);
        if (stationDTOUpdate.getOwnerId() != null) {
            User owner = validateOwner(stationDTOUpdate.getOwnerId());
            station.setOwner(owner);
        }
        station = stationRepository.save(station);
        return StationMapper.toStationDTO(station);
    }

    @Override
    public StationDTO delete(int id) throws CustomNotFoundException {
        Station station = optionalValidate.getStationById(id);
        stationRepository.delete(station);
        return StationMapper.toStationDTO(station);
    }
}
