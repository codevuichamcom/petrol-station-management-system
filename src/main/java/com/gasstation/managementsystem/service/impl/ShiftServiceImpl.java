package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;
import com.gasstation.managementsystem.model.mapper.ShiftMapper;
import com.gasstation.managementsystem.repository.ShiftRepository;
import com.gasstation.managementsystem.service.ShiftService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listShiftToMap(List<Shift> shifts) {
        List<ShiftDTO> shiftDTOS = new ArrayList<>();
        for (Shift shift : shifts) {
            shiftDTOS.add(ShiftMapper.toShiftDTO(shift));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", shiftDTOS);
        return map;
    }


    @Override
    public HashMap<String, Object> findAll(Sort sort) {
        return listShiftToMap(shiftRepository.findAll(sort));
    }

    @Override
    public ShiftDTO findById(int id) throws CustomNotFoundException {
        return ShiftMapper.toShiftDTO(optionalValidate.getShiftById(id));
    }

    @Override
    public ShiftDTO create(ShiftDTOCreate shiftDTOCreate) throws CustomNotFoundException {
        Shift shift = ShiftMapper.toShift(shiftDTOCreate);
        shift.setStation(optionalValidate.getStationById(shiftDTOCreate.getStationId()));
        trimString(shift);
        shift = shiftRepository.save(shift);
        return ShiftMapper.toShiftDTO(shift);
    }

    private void trimString(Shift shift) {
        shift.setName(StringUtils.trim(shift.getName()));
    }

    @Override
    public ShiftDTO update(int id, ShiftDTOUpdate shiftDTOUpdate) throws CustomNotFoundException {
        Shift shift = optionalValidate.getShiftById(id);
        ShiftMapper.copyNonNullToShift(shift, shiftDTOUpdate);
        trimString(shift);
        shift = shiftRepository.save(shift);
        return ShiftMapper.toShiftDTO(shift);
    }


    @Override
    public ShiftDTO delete(int id) throws CustomNotFoundException {
        Shift shift = optionalValidate.getShiftById(id);
        shiftRepository.delete(shift);
        return ShiftMapper.toShiftDTO(shift);
    }
}
