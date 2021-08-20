package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.tank.TankDTO;
import com.gasstation.managementsystem.model.dto.tank.TankDTOCreate;
import com.gasstation.managementsystem.model.dto.tank.TankDTOUpdate;
import com.gasstation.managementsystem.model.mapper.TankMapper;
import com.gasstation.managementsystem.repository.PriceChangeHistoryRepository;
import com.gasstation.managementsystem.repository.TankRepository;
import com.gasstation.managementsystem.service.TankService;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TankServiceImpl implements TankService {
    private final TankRepository tankRepository;
    private final OptionalValidate optionalValidate;
    private final PriceChangeHistoryRepository priceChangeHistoryRepository;
    private final UserHelper userHelper;

    private HashMap<String, Object> listTankToMap(List<Tank> tanks) {
        List<TankDTO> tankDTOS = tanks.stream().map(TankMapper::toTankDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", tankDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        List<Tank> tankList = new ArrayList<>();
        switch (userType.getId()) {
            case UserType.ADMIN:
                tankList = tankRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
                break;
            case UserType.OWNER:
                tankList = tankRepository.findAllByOwnerId(userLoggedIn.getId(), Sort.by(Sort.Direction.DESC, "id"));
                break;
        }
        return listTankToMap(tankList);
    }

    @Override
    public TankDTO findById(int id) throws CustomNotFoundException {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        Tank tank = optionalValidate.getTankById(id);
        if (userType.getId() == UserType.OWNER && !tank.getStation().getOwner().getId().equals(userLoggedIn.getId())) {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found")
                    .message("Tank not of the owner")
                    .table("tank_tbl").build());
        }
        return TankMapper.toTankDTO(tank);
    }

    @Override
    public TankDTO create(TankDTOCreate tankDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        checkDuplicate(tankDTOCreate.getName(), tankDTOCreate.getStationId());
        Station station = optionalValidate.getStationById(tankDTOCreate.getStationId());
        Fuel fuel = optionalValidate.getFuelById(tankDTOCreate.getFuelId());
        Tank tank = TankMapper.toTank(tankDTOCreate);
        tank.setStation(station);
        tank.setFuel(fuel);
        trimString(tank);
        tank = tankRepository.save(tank);
        return TankMapper.toTankDTO(tank);
    }

    private void trimString(Tank tank) {
        tank.setName(StringUtils.trim(tank.getName()));
    }

    private void checkDuplicate(String name, Integer stationId) throws CustomDuplicateFieldException {
        name = StringUtils.trim(name);
        if (name != null && stationId != null) {
            Optional<Tank> tankOptional = tankRepository.findByNameAndStationId(name, stationId);
            if (tankOptional.isPresent()) {
                throw new CustomDuplicateFieldException(CustomError.builder()
                        .code("duplicate").field("(name,stationId)").message("Name and stationId is duplicate").table("tank_table").build());
            }
        }
    }

    @Override
    @Transactional
    public TankDTO update(int id, TankDTOUpdate tankDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Tank oldTank = optionalValidate.getTankById(id);
        String name = StringUtils.trim(tankDTOUpdate.getName());
        if (needCheckDuplicate(name, oldTank)) {
            checkDuplicate(name, oldTank.getStation().getId());
        }
        Double newPrice = tankDTOUpdate.getCurrentPrice();
        double oldPrice = oldTank.getCurrentPrice();
        TankMapper.copyNonNullToTank(oldTank, tankDTOUpdate);
        trimString(oldTank);
        oldTank = tankRepository.save(oldTank);
        if (newPrice != null && newPrice != oldPrice) {
            User editor = userHelper.getUserLogin();
            PriceChangeHistory priceChangeHistory = PriceChangeHistory.builder()
                    .time(DateTimeHelper.getCurrentUnixTime())
                    .oldPrice(oldPrice)
                    .newPrice(newPrice)
                    .editor(editor)
                    .station(oldTank.getStation())
                    .tank(oldTank).build();
            priceChangeHistoryRepository.save(priceChangeHistory);
        }

        return TankMapper.toTankDTO(oldTank);
    }

    private boolean needCheckDuplicate(String name, Tank oldTank) {
        name = StringUtils.trim(name);
        return name != null && !name.equals(oldTank.getName());
    }


    @Override
    public TankDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException {
        Tank tank = optionalValidate.getTankById(id);
        try {
            tankRepository.delete(tank);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("fk_constraint")
                    .field("tank_id")
                    .message("Tank in use").build());
        }
        return TankMapper.toTankDTO(tank);
    }
}
