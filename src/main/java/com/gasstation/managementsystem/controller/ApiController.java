package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.api.ApiDTO;
import com.gasstation.managementsystem.model.dto.api.ApiDTOCreate;
import com.gasstation.managementsystem.model.dto.api.ApiDTOUpdate;
import com.gasstation.managementsystem.service.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "Api", description = "API for api")
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;

    @Operation(summary = "View All api")
    @GetMapping("/apis")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (pageSize != null) {
            return apiService.findAll(PageRequest.of(pageIndex - 1, pageSize));
        }
        return apiService.findAll();
    }

    @Operation(summary = "Find api by id")
    @GetMapping("/apis/{id}")
    public ApiDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return apiService.findById(id);
    }

    @Operation(summary = "Create new api")
    @PostMapping("/apis")
    public ApiDTO create(@Valid @RequestBody ApiDTOCreate apiDTOCreate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return apiService.create(apiDTOCreate);
    }

    @Operation(summary = "Create new Permission for typeId")
    @PostMapping("/apis/{id}/userTypes/{userTypeId}")
    public ApiDTO createPermission(@PathVariable(name = "id") Integer id,
                                   @PathVariable(name = "userTypeId") Integer userTypeId) throws CustomNotFoundException, CustomDuplicateFieldException {

        return apiService.addPermission(id, userTypeId);
    }

    @Operation(summary = "Update api by id")
    @PutMapping("/apis/{id}")
    public ApiDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody ApiDTOUpdate apiDTOUpdate) throws CustomNotFoundException, CustomDuplicateFieldException {
        return apiService.update(id, apiDTOUpdate);
    }

    @Operation(summary = "Delete api by id")
    @DeleteMapping("/apis/{id}")
    public ApiDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return apiService.delete(id);
    }

    @Operation(summary = "Delete Permission of typeId")
    @DeleteMapping("/apis/{id}/userTypes/{userTypeId}")
    public ApiDTO deletePermission(@PathVariable(name = "id") Integer id,
                                   @PathVariable(name = "userTypeId") Integer userTypeId) throws CustomNotFoundException {
        return apiService.deletePermission(id, userTypeId);
    }
}
