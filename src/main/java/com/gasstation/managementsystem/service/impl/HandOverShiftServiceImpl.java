package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.HandOverShift;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOUpdate;
import com.gasstation.managementsystem.model.mapper.HandOverShiftMapper;
import com.gasstation.managementsystem.repository.HandOverShiftRepository;
import com.gasstation.managementsystem.service.HandOverShiftService;
import com.gasstation.managementsystem.utils.OptionalValidate;
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
        HandOverShift handOverShift = HandOverShiftMapper.toHandOverShift(handOverShiftDTOCreate);
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
        handOverShift.setCreatedDate(localDateTime.atZone(TimeZone.getDefault().toZoneId()).toEpochSecond() * 1000);
        handOverShift.setShift(optionalValidate.getShiftById(handOverShiftDTOCreate.getShiftId()));
        handOverShift.setPump(optionalValidate.getPumpById(handOverShiftDTOCreate.getPumpId()));
        handOverShift = handOverShiftRepository.save(handOverShift);
        return HandOverShiftMapper.toHandOverShiftDTO(handOverShift);
    }

    @Override
    public HandOverShiftDTO update(int id, HandOverShiftDTOUpdate handOverShiftDTOUpdate) {
        return null;
    }

    @Override
    public HandOverShiftDTO delete(int id) {
        return null;
    }


}
