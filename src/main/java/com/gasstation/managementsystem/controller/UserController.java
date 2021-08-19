package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.exception.custom.CustomBadRequestException;
import com.gasstation.managementsystem.exception.custom.CustomDuplicateFieldException;
import com.gasstation.managementsystem.exception.custom.CustomNotFoundException;
import com.gasstation.managementsystem.model.dto.user.UserDTO;
import com.gasstation.managementsystem.model.dto.user.UserDTOCreate;
import com.gasstation.managementsystem.model.dto.user.UserDTOUpdate;
import com.gasstation.managementsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
@Tag(name = "User", description = "API for User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "View All user")
    @GetMapping("/users")
    public HashMap<String, Object> getAll(@RequestParam(name = "userTypeId", required = false) Integer userTypeId) {
        if (userTypeId != null) {
            return userService.findByUserTypeId(userTypeId);
        }
        return userService.findAll();
    }

    @Operation(summary = "Find user by id")
    @GetMapping("/users/{id}")
    public UserDTO getOne(@PathVariable(name = "id") Integer id) throws CustomNotFoundException {
        return userService.findById(id);
    }

    @Operation(summary = "Create new user")
    @PostMapping("/users")
    public UserDTO create(@Valid @RequestBody UserDTOCreate userDTOCreate) throws CustomDuplicateFieldException, CustomBadRequestException, CustomNotFoundException {
        return userService.create(userDTOCreate);
    }

    @Operation(summary = "Update user by id")
    @PutMapping("/users/{id}")
    public UserDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody UserDTOUpdate userDTOUpdate) throws CustomDuplicateFieldException, CustomNotFoundException, CustomBadRequestException {
        return userService.update(id, userDTOUpdate);
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/users/{id}")
    public UserDTO delete(@PathVariable(name = "id") Integer id) throws CustomNotFoundException, CustomBadRequestException {
        return userService.delete(id);
    }
}
