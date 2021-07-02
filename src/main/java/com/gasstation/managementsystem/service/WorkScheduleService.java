package com.gasstation.managementsystem.service;

import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;

public interface WorkScheduleService {

    WorkScheduleDTO create(WorkScheduleDTOCreate workScheduleDTOCreate) throws CustomNotFoundException;

    WorkScheduleDTO update(int id, WorkScheduleDTOUpdate workScheduleDTOUpdate);

    WorkScheduleDTO delete(int id) throws CustomNotFoundException;
}
