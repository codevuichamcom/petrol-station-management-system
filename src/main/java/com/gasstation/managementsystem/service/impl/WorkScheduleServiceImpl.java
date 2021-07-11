package com.gasstation.managementsystem.service.impl;

import com.gasstation.managementsystem.entity.WorkSchedule;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
import com.gasstation.managementsystem.model.mapper.WorkScheduleMapper;
import com.gasstation.managementsystem.repository.WorkScheduleRepository;
import com.gasstation.managementsystem.service.WorkScheduleService;
import com.gasstation.managementsystem.utils.OptionalValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;
    private final OptionalValidate optionalValidate;

    private HashMap<String, Object> listWorkScheduleToMap(List<WorkSchedule> workSchedules) {
        List<WorkScheduleDTO> workScheduleDTOS = workSchedules.stream().map(WorkScheduleMapper::toWorkScheduleDTO).collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", workScheduleDTOS);
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        Page<WorkSchedule> workSchedules = workScheduleRepository.findAll(pageable);
        HashMap<String, Object> map = listWorkScheduleToMap(workSchedules.getContent());
        map.put("totalElement", workSchedules.getTotalElements());
        map.put("totalPage", workSchedules.getTotalPages());
        return map;
    }

    @Override
    public HashMap<String, Object> findAll(Sort sort) {
        return listWorkScheduleToMap(workScheduleRepository.findAll(sort));
    }

    @Override
    public WorkScheduleDTO findById(int id) throws CustomNotFoundException {
        return WorkScheduleMapper.toWorkScheduleDTO(optionalValidate.getWorkScheduleById(id));
    }

    @Override
    public WorkScheduleDTO create(WorkScheduleDTOCreate workScheduleDTOCreate) throws CustomNotFoundException {
        WorkSchedule workSchedule = WorkScheduleMapper.toWorkSchedule(workScheduleDTOCreate);
        workSchedule.setEmployee(optionalValidate.getEmployeeById(workScheduleDTOCreate.getEmployeeId()));
        workSchedule.setShift(optionalValidate.getShiftById(workScheduleDTOCreate.getShiftId()));
        workSchedule = workScheduleRepository.save(workSchedule);
        return WorkScheduleMapper.toWorkScheduleDTO(workSchedule);
    }

    @Override
    public WorkScheduleDTO update(int id, WorkScheduleDTOUpdate workScheduleDTOUpdate) throws CustomNotFoundException {
        WorkSchedule workSchedule = optionalValidate.getWorkScheduleById(id);
        WorkScheduleMapper.copyNonNullToWorkSchedule(workSchedule, workScheduleDTOUpdate);
        workSchedule = workScheduleRepository.save(workSchedule);
        return WorkScheduleMapper.toWorkScheduleDTO(workSchedule);

    }

    @Override
    public WorkScheduleDTO delete(int id) throws CustomNotFoundException {
        WorkSchedule workSchedule = optionalValidate.getWorkScheduleById(id);
        workScheduleRepository.delete(workSchedule);
        return WorkScheduleMapper.toWorkScheduleDTO(workSchedule);
    }
}
