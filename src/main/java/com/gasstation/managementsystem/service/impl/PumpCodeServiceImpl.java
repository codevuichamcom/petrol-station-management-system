package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.PumpCode;
import com.gasstation.managementsystem.model.dto.PumpCodeDTO;
import com.gasstation.managementsystem.repository.PumpCodeRepository;
import com.gasstation.managementsystem.service.PumpCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PumpCodeServiceImpl implements PumpCodeService {
    @Autowired
    PumpCodeRepository pumpCodeRepository;

    @Override
    public List<PumpCodeDTO> findAll() {
        List<PumpCode> pumpCodes = pumpCodeRepository.findAll();
        List<PumpCodeDTO> pumpCodeDTOS = new ArrayList<>();
        for (PumpCode pumpCode : pumpCodes) {
            pumpCodeDTOS.add(new PumpCodeDTO(pumpCode));
        }
        return pumpCodeDTOS;
    }

    @Override
    public PumpCodeDTO findById(int id) {
        return new PumpCodeDTO(pumpCodeRepository.findById(id).get());
    }

    @Override
    public PumpCodeDTO save(PumpCode pumpCode) {
        pumpCodeRepository.save(pumpCode);
        return new PumpCodeDTO(pumpCode);
    }

    @Override
    public PumpCodeDTO delete(int id) {
        PumpCode pumpCode = pumpCodeRepository.findById(id).get();
        if (pumpCode != null) {
            pumpCodeRepository.delete(pumpCode);
            return new PumpCodeDTO(pumpCode);
        }
        return null;
    }
}
