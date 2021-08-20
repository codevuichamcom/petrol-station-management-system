package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Pump;
import com.gasstation.managementsystem.entity.Tank;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
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
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
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
    private final UserHelper userHelper;

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
    public HashMap<String, Object> findAll() {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        List<Pump> pumpList = new ArrayList<>();
        switch (userType.getId()) {
            case UserType.ADMIN:
                pumpList = pumpRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
                break;
            case UserType.OWNER:
                pumpList = pumpRepository.findAllByOwnerId(userLoggedIn.getId(), Sort.by(Sort.Direction.DESC, "id"));
                break;
        }
        return listPumpToMap(pumpList);
    }


    @Override
    public HashMap<String, Object> findAllByStationId(int stationId) {
        return listPumpToMap(pumpRepository.findAllByStationId(stationId, Sort.by(Sort.Direction.DESC, "id")));
    }

    @Override
    public PumpDTO findById(int id) throws CustomNotFoundException {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        Pump pump = optionalValidate.getPumpById(id);
        if (userType.getId() == UserType.OWNER && !pump.getTank().getStation().getOwner().getId().equals(userLoggedIn.getId())) {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found")
                    .message("Pump not of the owner")
                    .table("pump_tbl").build());
        }
        return PumpMapper.toPumpDTO(pump);
    }

    @Override
    public PumpDTO create(PumpDTOCreate pumpDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Tank tank = optionalValidate.getTankById(pumpDTOCreate.getTankId());
        checkDuplicate(pumpDTOCreate.getName(), tank.getStation().getId());
        Pump pump = PumpMapper.toPump(pumpDTOCreate);
        pump.setTank(tank);
        trimString(pump);
        pump = pumpRepository.save(pump);
        return PumpMapper.toPumpDTO(pump);
    }

    private void trimString(Pump pump) {
        pump.setName(StringUtils.trim(pump.getName()));
        pump.setNote(StringUtils.trim(pump.getNote()));
    }

    private void checkDuplicate(String name, Integer stationId) throws CustomDuplicateFieldException {
        if (name != null && stationId != null) {
            Optional<Pump> pumpOptional = pumpRepository.findByNameAndStationId(name.trim(), stationId);
            if (pumpOptional.isPresent()) {
                throw new CustomDuplicateFieldException(CustomError.builder()
                        .code("duplicate").field("(name,stationId)").message("Name and stationId is duplicate").table("pump_table").build());
            }
        }
    }

    @Override
    public PumpDTO update(int id, PumpDTOUpdate pumpDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Pump oldPump = optionalValidate.getPumpById(id);
        String name = StringUtils.trim(pumpDTOUpdate.getName());
        if (needCheckDuplicate(name, oldPump)) {
            checkDuplicate(name, oldPump.getTank().getStation().getId());
        }
        PumpMapper.copyNonNullToFuel(oldPump, pumpDTOUpdate);
        trimString(oldPump);
        oldPump = pumpRepository.save(oldPump);
        return PumpMapper.toPumpDTO(oldPump);
    }

    private boolean needCheckDuplicate(String name, Pump oldPump) {
        name = StringUtils.trim(name);
        return (name != null && !name.equalsIgnoreCase(oldPump.getName()));
    }

    @Override
    public PumpDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException {
        Pump pump = optionalValidate.getPumpById(id);
        try {
            pumpRepository.delete(pump);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("fk_constraint")
                    .field("pump_id")
                    .message("Pump in use").build());
        }
        return PumpMapper.toPumpDTO(pump);
    }
}
