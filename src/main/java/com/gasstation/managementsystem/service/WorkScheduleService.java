package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
import org.springframework.data.domain.Sort;

import java.util.HashMap;

public interface WorkScheduleService {

    HashMap<String, Object> findAll(Sort sort);

    WorkScheduleDTO findById(int employeeId, int shiftId) throws CustomNotFoundException;

    WorkScheduleDTO create(WorkScheduleDTOCreate workScheduleDTOCreate) throws CustomNotFoundException;

    WorkScheduleDTO update(int employeeId, int shiftId, WorkScheduleDTOUpdate workScheduleDTOUpdate) throws CustomNotFoundException;

    WorkScheduleDTO delete(int employeeId, int shiftId) throws CustomNotFoundException;
}
