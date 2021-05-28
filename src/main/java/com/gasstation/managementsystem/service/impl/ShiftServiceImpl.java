package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.model.dto.ShiftDTO;
import com.gasstation.managementsystem.repository.ShiftRepository;
import com.gasstation.managementsystem.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {
    @Autowired
    ShiftRepository shiftRepository;

    @Override
    public List<ShiftDTO> findAll() {
        List<Shift> shifts = shiftRepository.findAll();
        List<ShiftDTO> fuelDTOS = new ArrayList<>();
        for (Shift shift : shifts) {
            fuelDTOS.add(new ShiftDTO(shift));
        }
        return fuelDTOS;
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
