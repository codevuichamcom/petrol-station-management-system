package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pump.PumpDTO;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOCreate;
import com.gasstation.managementsystem.model.dto.pump.PumpDTOUpdate;
import com.gasstation.managementsystem.model.mapper.PumpMapper;
import com.gasstation.managementsystem.repository.PumpRepository;
import com.gasstation.managementsystem.service.PumpService;
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
public class PumpServiceImpl implements PumpService {
    private final PumpRepository pumpRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listPumpToMap(List<Pump> pumps) {
        List<PumpDTO> pumpDTOS = new ArrayList<>();
        for (Pump pump : pumps) {
            pumpDTOS.add(PumpMapper.toPumpDTO(pump));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", pumpDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<Pump> pumps = pumpRepository.findAll(pageable);
        HashMap<String, Object> map = listPumpToMap(pumps.getContent());
        map.put("totalElement", pumps.getTotalElements());
        map.put("totalPage", pumps.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listPumpToMap(pumpRepository.findAll());
    }

    @Override
    public PumpDTO findById(int id) throws CustomNotFoundException {
        return PumpMapper.toPumpDTO(optionalValidate.getPumpById(id));
    }

    @Override
    public PumpDTO create(PumpDTOCreate pumpDTOCreate) {
        Pump pump = PumpMapper.toPump(pumpDTOCreate);
        pump = pumpRepository.save(pump);
        return PumpMapper.toPumpDTO(pump);
    }

    @Override
    public PumpDTO update(int id, PumpDTOUpdate pumpDTOUpdate) throws CustomNotFoundException {
        Pump pump = optionalValidate.getPumpById(id);
        PumpMapper.copyNonNullToFuel(pump, pumpDTOUpdate);
        pumpRepository.save(pump);
        return PumpMapper.toPumpDTO(pump);
    }

    @Override
    public PumpDTO delete(int id) throws CustomNotFoundException {
        Pump pump = optionalValidate.getPumpById(id);
        pumpRepository.delete(pump);
        return PumpMapper.toPumpDTO(pump);
    }
}
