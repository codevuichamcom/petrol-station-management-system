package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.HandOverShift;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTO;
import com.gasstation.managementsystem.model.dto.handOverShift.HandOverShiftDTOFilter;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.mapper.HandOverShiftMapper;
import com.gasstation.managementsystem.repository.HandOverShiftRepository;
import com.gasstation.managementsystem.repository.criteria.HandOverShiftRepositoryCriteria;
import com.gasstation.managementsystem.service.HandOverShiftService;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HandOverShiftServiceImpl implements HandOverShiftService {
    private final HandOverShiftRepository handOverShiftRepository;
    private final HandOverShiftRepositoryCriteria handOverShiftCriteria;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;

    private HashMap<String, Object> listHandOverShiftToMap(List<HandOverShift> handOverShifts) {
        if (handOverShifts == null) return new HashMap<>();
        List<HandOverShiftDTO> tankDTOS = handOverShifts.stream().map(HandOverShiftMapper::toHandOverShiftDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", tankDTOS);
        return map;
    }

    public HashMap<String, Object> findAll(HandOverShiftDTOFilter filter) {
        HashMap<String, Object> temp = handOverShiftCriteria.findAll(filter);
        HashMap<String, Object> map = listHandOverShiftToMap((List<HandOverShift>) temp.get("data"));
        map.put("totalElement", temp.get("totalElement"));
        map.put("totalPage", temp.get("totalPage"));
        return map;

    }

    @Override
    public HandOverShiftDTO findById(int id) throws CustomNotFoundException {
        return HandOverShiftMapper.toHandOverShiftDTO(optionalValidate.getHandOverShiftById(id));
    }

    @Override
    public HandOverShiftDTO update(int id) throws CustomNotFoundException {
        HandOverShift oldHandOverShift = optionalValidate.getHandOverShiftById(id);
        oldHandOverShift.setClosedTime(DateTimeHelper.getCurrentUnixTime());
        oldHandOverShift.setExecutor(userHelper.getUserLogin());
        return HandOverShiftMapper.toHandOverShiftDTO(oldHandOverShift);
    }

    @Override
    public HashMap<String, Object> updateAllByStationId(int stationId) throws CustomNotFoundException {
        optionalValidate.getStationById(stationId);
        List<HandOverShift> handOverShifts = handOverShiftRepository.findAllByStationId(stationId);
        HashMap<String, Object> map = new HashMap<>();
        if (handOverShifts != null && handOverShifts.size() != 0) {
            long currentTime = DateTimeHelper.getCurrentUnixTime();
            for (HandOverShift handOverShift : handOverShifts) {
                handOverShift.setClosedTime(currentTime);
                handOverShift.setExecutor(userHelper.getUserLogin());
            }
            handOverShiftRepository.saveAll(handOverShifts);
            List<HandOverShiftDTO> handOverShiftDTOS = new ArrayList<>();
            for (HandOverShift handOverShift : handOverShifts) {
                User executor = handOverShift.getExecutor();
                UserDTO executorDTO = executor != null ? UserDTO.builder().id(executor.getId()).name(executor.getName()).build() : null;
                handOverShiftDTOS.add(HandOverShiftDTO.builder()
                        .id(handOverShift.getId())
                        .closedTime(handOverShift.getClosedTime())
                        .executor(executorDTO).build());
            }
            map.put("data", handOverShiftDTOS);
        }
        return map;
    }


}
