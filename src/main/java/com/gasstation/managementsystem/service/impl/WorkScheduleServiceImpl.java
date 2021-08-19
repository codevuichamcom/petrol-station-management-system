package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.*;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.CustomError;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
import com.gasstation.managementsystem.model.mapper.WorkScheduleMapper;
import com.gasstation.managementsystem.repository.WorkScheduleRepository;
import com.gasstation.managementsystem.service.WorkScheduleService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import com.gasstation.managementsystem.utils.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;
    private final OptionalValidate optionalValidate;
    private final UserHelper userHelper;

    private HashMap<String, Object> listWorkScheduleToMap(List<WorkSchedule> workSchedules) {
        List<WorkScheduleDTO> workScheduleDTOS = workSchedules.stream().map(WorkScheduleMapper::toWorkScheduleDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", workScheduleDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll() {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        List<WorkSchedule> workScheduleList = new ArrayList<>();
        switch (userType.getId()) {
            case UserType.ADMIN:
                workScheduleList = workScheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
                break;
            case UserType.OWNER:
                workScheduleList = workScheduleRepository.findAllByOwnerId(userLoggedIn.getId(), Sort.by(Sort.Direction.DESC, "id"));
        }
        return listWorkScheduleToMap(workScheduleList);
    }

    @Override
    public WorkScheduleDTO findById(int id) throws CustomNotFoundException {
        User userLoggedIn = userHelper.getUserLogin();
        UserType userType = userLoggedIn.getUserType();
        WorkSchedule workSchedule = optionalValidate.getWorkScheduleById(id);
        if (userType.getId() == UserType.OWNER && !userLoggedIn.getId().equals(workSchedule.getShift().getStation().getOwner().getId())) {
            throw new CustomNotFoundException(CustomError.builder()
                    .code("not.found")
                    .field("id")
                    .message("work schedule not of the owner")
                    .table("work_schedule_tbl").build());
        }
        return WorkScheduleMapper.toWorkScheduleDTO(workSchedule);
    }

    @Override
    public WorkScheduleDTO create(WorkScheduleDTOCreate workScheduleDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        WorkSchedule workSchedule = WorkScheduleMapper.toWorkSchedule(workScheduleDTOCreate);
        Employee employee = optionalValidate.getEmployeeById(workScheduleDTOCreate.getEmployeeId());
        Shift shift = optionalValidate.getShiftById(workScheduleDTOCreate.getShiftId());
        List<WorkSchedule> workScheduleList = employee.getWorkScheduleList();
        for (WorkSchedule schedule : workScheduleList) {
            if (Objects.equals(schedule.getShift().getId(), shift.getId())) {
                checkIntersectDate(workSchedule, schedule);
            }
        }
        workSchedule.setEmployee(employee);
        workSchedule.setShift(shift);
        workSchedule = workScheduleRepository.save(workSchedule);
        return WorkScheduleMapper.toWorkScheduleDTO(workSchedule);
    }

    private void checkIntersectDate(WorkSchedule newSchedule, WorkSchedule oldSchedule) throws CustomDuplicateFieldException {
        long secondInDay = 86400000L;
        Long oldMinStart = oldSchedule.getStartDate() / secondInDay;
        Long oldMinEnd = oldSchedule.getEndDate() / secondInDay;
        Long newMinStart = newSchedule.getStartDate() / secondInDay;
        Long newMinEnd = newSchedule.getEndDate() / secondInDay;
        if (inRange(oldMinStart, newMinStart, newMinEnd)
                || inRange(oldMinEnd, newMinStart, newMinEnd)
                || inRange(newMinStart, oldMinStart, oldMinEnd)
                || inRange(newMinEnd, oldMinStart, oldMinEnd)) {
            throw new CustomDuplicateFieldException(CustomError.builder()
                    .code("intersect")
                    .field("time")
                    .message("Work schedule is existed")
                    .table("work_schedule_tbl").build());
        }
    }

    private boolean inRange(Long value, Long start, Long end) {
        return value >= start && value <= end;
    }

    @Override
    public WorkScheduleDTO update(int id, WorkScheduleDTOUpdate workScheduleDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        WorkSchedule oldWorkSchedule = optionalValidate.getWorkScheduleById(id);
        WorkScheduleMapper.copyNonNullToWorkSchedule(oldWorkSchedule, workScheduleDTOUpdate);
        Integer shiftId = workScheduleDTOUpdate.getShiftId();
        if (shiftId != null) {
            Employee oldEmployee = oldWorkSchedule.getEmployee();
            List<WorkSchedule> workScheduleList = oldEmployee.getWorkScheduleList();
            for (WorkSchedule schedule : workScheduleList) {
                if (schedule.getId().equals(oldWorkSchedule.getId())) continue; //bỏ qua so với chính nó
                if (Objects.equals(schedule.getShift().getId(), shiftId)) {
                    checkIntersectDate(oldWorkSchedule, schedule);
                }
            }
            oldWorkSchedule.setShift(optionalValidate.getShiftById(shiftId));
        }
        oldWorkSchedule = workScheduleRepository.save(oldWorkSchedule);
        return WorkScheduleMapper.toWorkScheduleDTO(oldWorkSchedule);

    }

    @Override
    public WorkScheduleDTO delete(int id) throws CustomNotFoundException, CustomBadRequestException {
        WorkSchedule workSchedule = optionalValidate.getWorkScheduleById(id);
        try {
            workScheduleRepository.delete(workSchedule);
        } catch (DataIntegrityViolationException ex) {
            throw new CustomBadRequestException(CustomError.builder()
                    .code("fk_constraint")
                    .field("work_schedule_id")
                    .message("Work schedule in use").build());
        }
        return WorkScheduleMapper.toWorkScheduleDTO(workSchedule);
    }
}
