package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.model.dto.ShiftDTO;
import com.gasstation.managementsystem.repository.ShiftRepository;
import com.gasstation.managementsystem.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {
    @Autowired
    ShiftRepository shiftRepository;

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Shift> shifts = shiftRepository.findAll(pageable);
        List<ShiftDTO> fuelDTOS = new ArrayList<>();
        for (Shift shift : shifts) {
            fuelDTOS.add(new ShiftDTO(shift));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", fuelDTOS);
        map.put("totalElement", shifts.getTotalElements());
        map.put("totalPage", shifts.getTotalPages());
        return map;
    }

    @Override
    public ShiftDTO findById(int id) {
        return new ShiftDTO(shiftRepository.findById(id).get());
    }

    @Override
    public ShiftDTO save(Shift shift) {
        shiftRepository.save(shift);
        return new ShiftDTO(shift);
    }

    @Override
    public ShiftDTO delete(int id) {
        Shift shift = shiftRepository.findById(id).get();
        if (shift != null) {
            shiftRepository.delete(shift);
            return new ShiftDTO(shift);
        }
        return null;
    }
}
