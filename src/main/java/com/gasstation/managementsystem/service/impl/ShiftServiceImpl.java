package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;
import com.gasstation.managementsystem.model.mapper.ShiftMapper;
import com.gasstation.managementsystem.repository.ShiftRepository;
import com.gasstation.managementsystem.service.ShiftService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Shift> shifts = shiftRepository.findAll(pageable);

        HashMap<String, Object> map = listShiftToMap(shifts.getContent());
        map.put("totalElement", shifts.getTotalElements());
        map.put("totalPage", shifts.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listShiftToMap(shiftRepository.findAll());
    }

    @Override
    public ShiftDTO findById(int id) throws CustomNotFoundException {
        return ShiftMapper.toShiftDTO(optionalValidate.getShiftById(id));
    }

    @Override
    public ShiftDTO create(ShiftDTOCreate shiftDTOCreate) throws CustomNotFoundException {
        Shift shift = ShiftMapper.toShift(shiftDTOCreate);
        User employee = optionalValidate.getUserById(shiftDTOCreate.getEmployeeId());
        User owner = optionalValidate.getUserById(shiftDTOCreate.getOwnerId());
        Station station = optionalValidate.getStationById(shiftDTOCreate.getStationId());
        shift.setEmployee(employee);
        shift.setOwner(owner);
        shift.setStation(station);
        shift = shiftRepository.save(shift);
        return ShiftMapper.toShiftDTO(shift);
    }

    @Override
    public ShiftDTO update(int id, ShiftDTOUpdate shiftDTOUpdate) throws CustomNotFoundException {
        Shift shift = optionalValidate.getShiftById(id);
        ShiftMapper.copyNonNullToTank(shift, shiftDTOUpdate);
        if (shiftDTOUpdate.getEmployeeId() != null) {
            User employee = optionalValidate.getUserById(shiftDTOUpdate.getEmployeeId());
            shift.setEmployee(employee);
        }
        if (shiftDTOUpdate.getOwnerId() != null) {
            User owner = optionalValidate.getUserById(shiftDTOUpdate.getOwnerId());
            shift.setOwner(owner);
        }
        if (shiftDTOUpdate.getStationId() != null) {
            Station station = optionalValidate.getStationById(shiftDTOUpdate.getStationId());
            shift.setStation(station);
        }
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
