package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public HashMap<String, Object> findAll(Pageable pageable, Sort sort) {
        Page<Pump> pumps = pumpRepository.findAll(pageable);
        HashMap<String, Object> map = listPumpToMap(pumps.getContent());
        map.put("totalElement", pumps.getTotalElements());
        map.put("totalPage", pumps.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Sort sort) {
        return listPumpToMap(pumpRepository.findAll(sort));
    }

    @Override
    public HashMap<String, Object> findAllByOwnerId(int ownerId, Sort sort) {
        return listPumpToMap(pumpRepository.findAllByOwnerId(ownerId, sort));
    }

    @Override
    public PumpDTO findById(int id) throws CustomNotFoundException {
        return PumpMapper.toPumpDTO(optionalValidate.getPumpById(id));
    }

    @Override
    public PumpDTO create(PumpDTOCreate pumpDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        needCheckDuplicate(pumpDTOCreate.getName(), pumpDTOCreate.getTankId());
        Pump pump = PumpMapper.toPump(pumpDTOCreate);
        Tank tank = optionalValidate.getTankById(pumpDTOCreate.getTankId());
        pump.setTank(tank);
        pump = pumpRepository.save(pump);
        return PumpMapper.toPumpDTO(pump);
    }

    private void needCheckDuplicate(String name, Integer tankId) throws CustomDuplicateFieldException {
        if (name == null || tankId == null) return;
        Optional<Pump> pumpOptional = pumpRepository.findByNameAndTankId(name, tankId);
        if (pumpOptional.isPresent()) {
            throw new CustomDuplicateFieldException(CustomError.builder()
                    .code("duplicate").field("(name,tankId)").message("Name and tankId is duplicate").table("pump_table").build());
        }
    }

    @Override
    public PumpDTO update(int id, PumpDTOUpdate pumpDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Pump oldPump = optionalValidate.getPumpById(id);
        String name = pumpDTOUpdate.getName();
        Integer tankId = pumpDTOUpdate.getTankId();

        if (needCheckDuplicate(name, tankId, oldPump)) {
            needCheckDuplicate(name, tankId);
        }
        PumpMapper.copyNonNullToFuel(oldPump, pumpDTOUpdate);
        if (tankId != null) {
            Tank tank = optionalValidate.getTankById(tankId);
            oldPump.setTank(tank);
        }
        oldPump = pumpRepository.save(oldPump);
        return PumpMapper.toPumpDTO(oldPump);
    }

    private boolean needCheckDuplicate(String name, Integer tankId, Pump oldPump) {
        if (name == null || tankId == null || oldPump.getTank() == null) return false;
        return !name.equalsIgnoreCase(oldPump.getName()) || tankId != oldPump.getTank().getId();
    }

    @Override
    public PumpDTO delete(int id) throws CustomNotFoundException {
        Pump pump = optionalValidate.getPumpById(id);
        pumpRepository.delete(pump);
        return PumpMapper.toPumpDTO(pump);
    }
}
