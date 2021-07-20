package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.HandOverShift;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.station.StationDTOUpdateHandOverShift;
import com.gasstation.managementsystem.model.mapper.HandOverShiftMapper;
import com.gasstation.managementsystem.repository.HandOverShiftRepository;
import com.gasstation.managementsystem.service.HandOverShiftService;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HandOverShiftServiceImpl implements HandOverShiftService {
    private final HandOverShiftRepository handOverShiftRepository;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;

    private HashMap<String, Object> listHandOverShiftToMap(List<HandOverShift> tanks) {
        List<HandOverShiftDTO> tankDTOS = tanks.stream().map(HandOverShiftMapper::toHandOverShiftDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", tankDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listHandOverShiftToMap(handOverShiftRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    @Override
    public HandOverShiftDTO findById(int id) throws CustomNotFoundException {
        return HandOverShiftMapper.toHandOverShiftDTO(optionalValidate.getHandOverShiftById(id));
    }

    @Override
    public HandOverShiftDTO create(HandOverShiftDTOCreate handOverShiftDTOCreate) throws CustomNotFoundException {
        HandOverShift handOverShift = new HandOverShift();
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
        handOverShift.setCreatedDate(localDateTime.atZone(TimeZone.getDefault().toZoneId()).toEpochSecond() * 1000);
        handOverShift.setShift(optionalValidate.getShiftById(handOverShiftDTOCreate.getShiftId()));
        handOverShift.setPump(optionalValidate.getPumpById(handOverShiftDTOCreate.getPumpId()));
        handOverShift = handOverShiftRepository.save(handOverShift);
        return HandOverShiftMapper.toHandOverShiftDTO(handOverShift);
    }

    @Override
    public HandOverShiftDTO update(int id) throws CustomNotFoundException {
        HandOverShift oldHandOverShift = optionalValidate.getHandOverShiftById(id);
        oldHandOverShift.setClosedTime(DateTimeHelper.getCurrentUnixTime());
        oldHandOverShift.setActor(userHelper.getUserLogin());
        return HandOverShiftMapper.toHandOverShiftDTO(oldHandOverShift);
    }

    @Override
    public void updateAllByStationId(StationDTOUpdateHandOverShift stationDTOUpdateHandOverShift) throws CustomNotFoundException {
        int stationId = stationDTOUpdateHandOverShift.getId();
        optionalValidate.getStationById(stationId);
        List<HandOverShift> handOverShifts = handOverShiftRepository.findAllByStationId(stationId);
        if (handOverShifts != null && handOverShifts.size() != 0) {
            for (HandOverShift handOverShift : handOverShifts) {
                handOverShift.setClosedTime(DateTimeHelper.getCurrentUnixTime());
                handOverShift.setActor(userHelper.getUserLogin());
            }
            handOverShiftRepository.saveAll(handOverShifts);
        }
    }


}
