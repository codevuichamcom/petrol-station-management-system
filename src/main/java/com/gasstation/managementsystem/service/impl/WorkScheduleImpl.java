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
import org.springframework.stereotype.Service;

@Service
//@Transactional
@RequiredArgsConstructor
public class WorkScheduleImpl implements WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;
    private final OptionalValidate optionalValidate;


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
    public WorkScheduleDTO update(int id, WorkScheduleDTOUpdate workScheduleDTOUpdate) {
//        WorkSchedulePrimary workSchedulePrimary = new WorkSchedulePrimary();
//        WorkSchedule oldWorkSchedule = workScheduleRepository.findById(WorkSchedulePrimary.);
        return null;
    }

    @Override
    public WorkScheduleDTO delete(int id) throws CustomNotFoundException {
        return null;
    }
}
