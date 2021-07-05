package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.WorkSchedule;
import com.gasstation.managementsystem.entity.primaryCombine.WorkSchedulePrimary;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
import com.gasstation.managementsystem.model.mapper.WorkScheduleMapper;
import com.gasstation.managementsystem.repository.WorkScheduleRepository;
import com.gasstation.managementsystem.service.WorkScheduleService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkScheduleImpl implements WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listWorkScheduleToMap(List<WorkSchedule> workSchedules) {
        List<WorkScheduleDTO> workScheduleDTOS = workSchedules.stream().map(WorkScheduleMapper::toWorkScheduleDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", workScheduleDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Sort sort) {
        return listWorkScheduleToMap(workScheduleRepository.findAll(sort));
    }

    @Override
    public WorkScheduleDTO findById(int employeeId, int shiftId) throws CustomNotFoundException {
        return WorkScheduleMapper.toWorkScheduleDTO(optionalValidate.getWorkScheduleByEmployeeIdAndShiftId(employeeId, shiftId));
    }

    @Override
    public WorkScheduleDTO create(WorkScheduleDTOCreate workScheduleDTOCreate) throws CustomNotFoundException {
        WorkSchedule workSchedule = WorkScheduleMapper.toWorkSchedule(workScheduleDTOCreate);
        workSchedule.setEmployee(optionalValidate.getEmployeeById(workScheduleDTOCreate.getEmployeeId()));
        workSchedule.setShift(optionalValidate.getShiftById(workScheduleDTOCreate.getShiftId()));
        WorkSchedulePrimary primary = new WorkSchedulePrimary();
        primary.setEmployeeId(workScheduleDTOCreate.getEmployeeId());
        primary.setShiftId(workScheduleDTOCreate.getShiftId());
        workSchedule.setId(primary);
        workSchedule = workScheduleRepository.save(workSchedule);
        return WorkScheduleMapper.toWorkScheduleDTO(workSchedule);
    }

    @Override
    public WorkScheduleDTO update(int employeeId, int shiftId, WorkScheduleDTOUpdate workScheduleDTOUpdate) throws CustomNotFoundException {
        WorkSchedule workSchedule = optionalValidate.getWorkScheduleByEmployeeIdAndShiftId(employeeId, shiftId);
        WorkScheduleMapper.copyNonNullToWorkSchedule(workSchedule, workScheduleDTOUpdate);
        workSchedule = workScheduleRepository.save(workSchedule);
        return WorkScheduleMapper.toWorkScheduleDTO(workSchedule);

    }

    @Override
    public WorkScheduleDTO delete(int employeeId, int shiftId) throws CustomNotFoundException {
        WorkSchedule workSchedule = optionalValidate.getWorkScheduleByEmployeeIdAndShiftId(employeeId, shiftId);
        workScheduleRepository.delete(workSchedule);
        return WorkScheduleMapper.toWorkScheduleDTO(workSchedule);
    }
}
