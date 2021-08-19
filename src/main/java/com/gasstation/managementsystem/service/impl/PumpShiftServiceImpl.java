package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.PumpShift;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTO;
import com.gasstation.managementsystem.model.dto.pumpShift.PumpShiftDTOFilter;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.mapper.PumpShiftMapper;
import com.gasstation.managementsystem.repository.PumpShiftRepository;
import com.gasstation.managementsystem.repository.criteria.PumpShiftRepositoryCriteria;
import com.gasstation.managementsystem.service.PumShiftService;
import com.gasstation.managementsystem.utils.DateTimeHelper;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PumpShiftServiceImpl implements PumShiftService {
    private final PumpShiftRepository pumpShiftRepository;
    private final PumpShiftRepositoryCriteria pumpShiftCriteria;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;

    private HashMap<String, Object> listPumpShiftToMap(List<PumpShift> pumpShifts) {
        if (pumpShifts == null) return new HashMap<>();
        List<PumpShiftDTO> tankDTOS = pumpShifts.stream().map(PumpShiftMapper::toPumpShiftDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", tankDTOS);
        return map;
    }

    public HashMap<String, Object> findAll(PumpShiftDTOFilter filter) {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        if (userType.getId() == UserType.OWNER) {
            if (filter.getStationIds() == null || filter.getStationIds().length == 0) {
                List<Integer> stationIds = userHelper.getListStationIdOfOwner(userLoggedIn);
                filter.setStationIds(stationIds.toArray(Integer[]::new));
            }
        }
        HashMap<String, Object> temp = pumpShiftCriteria.findAll(filter);
        HashMap<String, Object> map = listPumpShiftToMap((List<PumpShift>) temp.get("data"));
        map.put("totalElement", temp.get("totalElement"));
        map.put("totalPage", temp.get("totalPage"));
        map.put("totalVolume", temp.get("totalVolume"));
        map.put("totalAmount", temp.get("totalAmount"));
        return map;

    }

    @Override
    public PumpShiftDTO findById(int id) throws CustomNotFoundException {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        PumpShift pumpShift = optionalValidate.getPumpShiftById(id);
        if (userType.getId() == UserType.OWNER
                && !pumpShift.getPump().getTank().getStation().getOwner().getId().equals(userLoggedIn.getId())) {//id không phải owner đang đăng nhâp
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found")
                    .message("Pump shift not of the owner")
                    .table("pump_shift_tbl").build());
        }
        return PumpShiftMapper.toPumpShiftDTO(pumpShift);
    }

    @Override
    public PumpShiftDTO update(int id) throws CustomNotFoundException {
        PumpShift oldPumpShift = optionalValidate.getPumpShiftById(id);
        oldPumpShift.setClosedTime(DateTimeHelper.getCurrentUnixTime());
        oldPumpShift.setExecutor(userHelper.getUserLogin());
        return PumpShiftMapper.toPumpShiftDTO(oldPumpShift);
    }

    @Override
    @Transactional
    public HashMap<String, Object> updateAllByStationId(int stationId) throws CustomNotFoundException {
        optionalValidate.getStationById(stationId);
        List<PumpShift> pumpShifts = pumpShiftRepository.findAllByStationId(stationId, DateTimeHelper.getCurrentUnixTime());
        HashMap<String, Object> map = new HashMap<>();
        if (pumpShifts != null && pumpShifts.size() != 0) {
            long currentTime = DateTimeHelper.getCurrentUnixTime();
            for (PumpShift pumpShift : pumpShifts) {
                pumpShift.setClosedTime(currentTime);
                pumpShift.setExecutor(userHelper.getUserLogin());
            }
            pumpShiftRepository.saveAll(pumpShifts);
            List<PumpShiftDTO> pumpShiftDTOS = new ArrayList<>();
            for (PumpShift pumpShift : pumpShifts) {
                User executor = pumpShift.getExecutor();
                UserDTO executorDTO = executor != null ? UserDTO.builder().id(executor.getId()).name(executor.getName()).build() : null;
                pumpShiftDTOS.add(PumpShiftDTO.builder()
                        .id(pumpShift.getId())
                        .closedTime(pumpShift.getClosedTime())
                        .executor(executorDTO).build());
            }
            map.put("data", pumpShiftDTOS);
        }
        return map;
    }


}
