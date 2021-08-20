package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.Shift;
import com.gasstation.managementsystem.entity.Station;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTO;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOCreate;
import com.gasstation.managementsystem.model.dto.shift.ShiftDTOUpdate;
import com.gasstation.managementsystem.model.mapper.ShiftMapper;
import com.gasstation.managementsystem.repository.ShiftRepository;
import com.gasstation.managementsystem.service.ShiftService;
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
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;

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
    public HashMap<String, Object> findAll() {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        List<Shift> shiftList = new ArrayList<>();
        switch (userType.getId()) {
            case UserType.ADMIN:
                shiftList = shiftRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
                break;
            case UserType.OWNER:
                shiftList = shiftRepository.findAllShiftByOwnerId(userLoggedIn.getId(), Sort.by(Sort.Direction.DESC, "id"));
                break;
        }
        return listShiftToMap(shiftList);
    }

    @Override
    public ShiftDTO findById(int id) throws CustomNotFoundException {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        Shift shift = optionalValidate.getShiftById(id);
        if (userType.getId() == UserType.OWNER && !userLoggedIn.getId().equals(shift.getStation().getOwner().getId())) {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found")
                    .message("Shift not of the owner")
                    .table("shift_tbl").build());
        }
        return ShiftMapper.toShiftDTO(shift);
    }

    @Override
    public ShiftDTO create(ShiftDTOCreate shiftDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        Station station = optionalValidate.getStationById(shiftDTOCreate.getStationId());
        checkDuplicateName(shiftDTOCreate.getName(), station.getId());
        List<Shift> shiftList = station.getShiftList();
        Shift newShift = ShiftMapper.toShift(shiftDTOCreate);

        for (Shift oldShift : shiftList) {
            checkIntersectShift(newShift, oldShift);
        }
        newShift.setStation(station);
        trimString(newShift);
        newShift = shiftRepository.save(newShift);
        return ShiftMapper.toShiftDTO(newShift);
    }

    private void checkDuplicateName(String name, Integer stationId) throws CustomDuplicateFieldException {
        name = StringUtils.trim(name);
        Optional<Shift> shiftOptional = shiftRepository.findByNameAndStationId(name, stationId);
        if (shiftOptional.isPresent()) {
            throw new CustomDuplicateFieldException(CustomError.builder()
                    .code("duplicate")
                    .field("name")
                    .message("Duplicate name in station")
                    .table("shift_tbl").build());
        }
    }

    private void checkIntersectShift(Shift newShift, Shift oldShift) throws CustomDuplicateFieldException {
        Long oldMinStart = oldShift.getStartTime();
        Long oldMinEnd = oldShift.getEndTime();
        Long newMinStart = newShift.getStartTime();
        Long newMinEnd = newShift.getEndTime();
        if (inRange(oldMinStart, newMinStart, newMinEnd)
                || inRange(oldMinEnd, newMinStart, newMinEnd)
                || inRange(newMinStart, oldMinStart, oldMinEnd)
                || inRange(newMinEnd, oldMinStart, oldMinEnd)) {
            throw new CustomDuplicateFieldException(CustomError.builder()
                    .code("intersect")
                    .field("time")
                    .message("Shift of station is existed")
                    .table("shift_tbl").build());
        }
    }

    private boolean inRange(Long value, Long start, Long end) {
        return value > start && value < end;
    }

    private void trimString(Shift shift) {
        shift.setName(StringUtils.trim(shift.getName()));
    }

    @Override
    public ShiftDTO update(int id, ShiftDTOUpdate shiftDTOUpdate) throws CustomNotFoundException, CustomBadRequestException, CustomDuplicateFieldException {
        Shift oldShift = optionalValidate.getShiftById(id);
        if (shiftDTOUpdate.getName() != null && !oldShift.getName().equals(StringUtils.trim(shiftDTOUpdate.getName()))) {
            checkDuplicateName(shiftDTOUpdate.getName(), oldShift.getStation().getId());
        }
        ShiftMapper.copyNonNullToShift(oldShift, shiftDTOUpdate);
        trimString(oldShift);
        if (shiftDTOUpdate.getStartTime() != null || shiftDTOUpdate.getEndTime() != null) {
            Station station = oldShift.getStation();
            List<Shift> shiftList = station.getShiftList();
            for (Shift shift : shiftList) {
                if (shift.getId() == oldShift.getId()) continue;//bỏ qua so với chính nó
                checkIntersectShift(oldShift, shift);
            }
        }
        oldShift = shiftRepository.save(oldShift);
        return ShiftMapper.toShiftDTO(oldShift);
    }


    @Override
    public ShiftDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException {
        Shift shift = optionalValidate.getShiftById(id);
        try {
            shiftRepository.delete(shift);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("fk_constraint")
                    .field("shift_id")
                    .message("Shift in use").build());
        }
        return ShiftMapper.toShiftDTO(shift);
    }
}
