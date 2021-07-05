package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
import com.gasstation.managementsystem.service.WorkScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping(value = Api.PREFIX)
@CrossOrigin
@Tag(name = "Work Schedule", description = "API for Work Schedule")
@RequiredArgsConstructor
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;

    @Operation(summary = "View All work schedule")
    @GetMapping("/work-schedules")
    public HashMap<String, Object> getAll() {
        return workScheduleService.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Operation(summary = "Find work schedule by id")
    @GetMapping("/work-schedules/employees/{employeeId}/shifts/{shiftId}")
    public WorkScheduleDTO getOne(@PathVariable(name = "employeeId") Integer employeeId,
                                  @PathVariable(name = "shiftId") Integer shiftId) throws CustomNotFoundException {
        return workScheduleService.findById(employeeId, shiftId);
    }

    @Operation(summary = "Create new work schedule")
    @PostMapping("/work-schedules")
    public WorkScheduleDTO create(@Valid @RequestBody WorkScheduleDTOCreate workScheduleDTOCreate) throws CustomNotFoundException {
        return workScheduleService.create(workScheduleDTOCreate);
    }

    @Operation(summary = "Update work schedule by id")
    @PutMapping("/work-schedules/employees/{employeeId}/shifts/{shiftId}")
    public WorkScheduleDTO update(@PathVariable(name = "employeeId") Integer employeeId,
                                  @PathVariable(name = "shiftId") Integer shiftId,
                                  @Valid @RequestBody WorkScheduleDTOUpdate workScheduleDTOUpdate) throws CustomNotFoundException {
        return workScheduleService.update(employeeId, shiftId, workScheduleDTOUpdate);
    }

    @Operation(summary = "Delete tank by id")
    @DeleteMapping("/work-schedules/employees/{employeeId}/shifts/{shiftId}")
    public WorkScheduleDTO delete(@PathVariable(name = "employeeId") Integer employeeId,
                                  @PathVariable(name = "shiftId") Integer shiftId) throws CustomNotFoundException {
        return workScheduleService.delete(employeeId, shiftId);
    }

}
