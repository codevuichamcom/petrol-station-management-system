package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Card;
import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.entity.PumpCode;
import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTO;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTOCreate;
import com.gasstation.managementsystem.model.dto.pumpCode.PumpCodeDTOUpdate;
import com.gasstation.managementsystem.model.mapper.PumpCodeMapper;
import com.gasstation.managementsystem.repository.PumpCodeRepository;
import com.gasstation.managementsystem.service.PumpCodeService;
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
public class PumpCodeServiceImpl implements PumpCodeService {
    private final PumpCodeRepository pumpCodeRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listPumpCodeToMap(List<PumpCode> pumpCodes) {
        List<PumpCodeDTO> pumpCodeDTOS = new ArrayList<>();
        for (PumpCode pumpCode : pumpCodes) {
            pumpCodeDTOS.add(PumpCodeMapper.toPumpCodeDTO(pumpCode));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", pumpCodeDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<PumpCode> pumpCodes = pumpCodeRepository.findAll(pageable);
        HashMap<String, Object> map = listPumpCodeToMap(pumpCodes.getContent());
        map.put("totalElement", pumpCodes.getTotalElements());
        map.put("totalPage", pumpCodes.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        return listPumpCodeToMap(pumpCodeRepository.findAll());
    }

    @Override
    public PumpCodeDTO findById(int id) throws CustomNotFoundException {
        return PumpCodeMapper.toPumpCodeDTO(optionalValidate.getPumpCodeById(id));
    }

    @Override
    public PumpCodeDTO create(PumpCodeDTOCreate pumpCodeDTOCreate) throws CustomNotFoundException {
        PumpCode pumpCode = PumpCodeMapper.toPumpCode(pumpCodeDTOCreate);
        Pump pump = optionalValidate.getPumpById(pumpCodeDTOCreate.getPumpId());
        Shift shift = optionalValidate.getShiftById(pumpCodeDTOCreate.getShiftId());
        Card card = optionalValidate.getCardById(pumpCodeDTOCreate.getCardId());
        pumpCode.setPump(pump);
        pumpCode.setShift(shift);
        pumpCode.setCard(card);
        pumpCode = pumpCodeRepository.save(pumpCode);
        return PumpCodeMapper.toPumpCodeDTO(pumpCode);
    }

    @Override
    public PumpCodeDTO update(int id, PumpCodeDTOUpdate pumpCodeDTOUpdate) throws CustomNotFoundException {
        PumpCode pumpCode = optionalValidate.getPumpCodeById(id);
        PumpCodeMapper.copyNonNullToFuel(pumpCode, pumpCodeDTOUpdate);
        Integer pumpId = pumpCodeDTOUpdate.getPumpId();
        Integer shiftId = pumpCodeDTOUpdate.getShiftId();
        Integer cardId = pumpCodeDTOUpdate.getCardId();
        if (pumpId != null) {
            pumpCode.setPump(optionalValidate.getPumpById(pumpId));
        }
        if (shiftId != null) {
            pumpCode.setShift(optionalValidate.getShiftById(shiftId));
        }
        if (cardId != null) {
            pumpCode.setCard(optionalValidate.getCardById(cardId));
        }
        pumpCode = pumpCodeRepository.save(pumpCode);
        return PumpCodeMapper.toPumpCodeDTO(pumpCode);
    }


    @Override
    public PumpCodeDTO delete(int id) throws CustomNotFoundException {
        PumpCode pumpCode = optionalValidate.getPumpCodeById(id);
        pumpCodeRepository.delete(pumpCode);
        return PumpCodeMapper.toPumpCodeDTO(pumpCode);
    }
}
