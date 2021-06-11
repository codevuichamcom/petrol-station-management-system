package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.userType.UserTypeDTO;
import com.gasstation.managementsystem.service.UserTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Tag(name = "User type", description = "API for user type")
public class UserTypeController {

    @Autowired
    UserTypeService userTypeService;

    @Operation(summary = "View All userType")
    @GetMapping("/user-types")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        if (pageSize == null) {
            return userTypeService.findAll();
        }
        return userTypeService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find userType by id")
    @GetMapping("/user-types/{id}")
    public UserTypeDTO getOne(@PathVariable(name = "id") Integer id) {
        return userTypeService.findById(id);
    }

    @Operation(summary = "Create new userType")
    @PostMapping("/user-types")
    public UserTypeDTO create(@Valid @RequestBody UserType userType) {
        return userTypeService.save(userType);
    }

    @Operation(summary = "Update userType by id")
    @PutMapping("/user-types/{id}")
    public UserTypeDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody UserType userType) {
        userType.setId(id);
        return userTypeService.save(userType);
    }

    @Operation(summary = "Delete userType by id")
    @DeleteMapping("/user-types/{id}")
    public UserTypeDTO delete(@PathVariable(name = "id") Integer id) {
        return userTypeService.delete(id);
    }
}
