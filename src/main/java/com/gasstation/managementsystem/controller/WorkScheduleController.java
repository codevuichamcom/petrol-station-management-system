package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Api;
import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTO;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOCreate;
import com.gasstation.managementsystem.model.dto.workSchedule.WorkScheduleDTOUpdate;
import com.gasstation.managementsystem.service.WorkScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        return workScheduleService.findAll();
    }

    @Operation(summary = "Find work schedule by id")
    @GetMapping("/work-schedules/{id}")
    public WorkScheduleDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return workScheduleService.findById(id);
    }

    @Operation(summary = "Create new work schedule")
    @PostMapping("/work-schedules")
    public WorkScheduleDTO create(@Valid @RequestBody WorkScheduleDTOCreate workScheduleDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return workScheduleService.create(workScheduleDTOCreate);
    }

    @Operation(summary = "Update work schedule by id")
    @PutMapping("/work-schedules/{id}")
    public WorkScheduleDTO update(@PathVariable(name = "id") Integer id,
                                  @Valid @RequestBody WorkScheduleDTOUpdate workScheduleDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return workScheduleService.update(id, workScheduleDTOUpdate);
    }

    @Operation(summary = "Delete work schedule by id")
    @DeleteMapping("/work-schedules/{id}")
    public WorkScheduleDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException, CustomBadRequestException {
        return workScheduleService.delete(id);
    }

}
