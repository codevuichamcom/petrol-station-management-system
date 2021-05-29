package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.User;
import com.gasstation.managementsystem.entity.UserType;
import com.gasstation.managementsystem.model.dto.AccountDTO;
import com.gasstation.managementsystem.model.dto.UserDTO;
import com.gasstation.managementsystem.model.dto.UserDTO;
import com.gasstation.managementsystem.model.dto.UserTypeDTO;
import com.gasstation.managementsystem.service.AccountService;
import com.gasstation.managementsystem.service.UserService;
import com.gasstation.managementsystem.service.UserTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
@Tag(name = "User", description = "API for User")
public class UserController {
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;
    @Autowired
    UserTypeService userTypeService;

    @Operation(summary = "View All user")
    @GetMapping("/users")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return userService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Find user by id")
    @GetMapping("/users/{id}")
    public UserDTO getOne(@PathVariable(name = "id") Integer id) {
        return userService.findById(id);
    }

    @Operation(summary = "Create new user")
    @PostMapping("/users")
    @Transactional
    public UserDTO create(@Valid @RequestBody User user) {
        if(user.getUserType()!=null){
            UserTypeDTO userType = userTypeService.findById(user.getUserType().getId());
            user.setUserType(new UserType(userType));
        }
        userService.save(user);
        Account account = user.getAccount();
        if(account!=null){
            account.setUserInfo(user);
            accountService.save(account);
        }
        return new UserDTO(user);

    }

    @Operation(summary = "Update user by id")
    @PutMapping("/users/{id}")
    public UserDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody User user) {
        user.setId(id);
        if(user.getUserType()!=null){
            UserTypeDTO userType = userTypeService.findById(user.getUserType().getId());
            user.setUserType(new UserType(userType));
        }
        Account account = user.getAccount();
        account.setUserInfo(user);
        accountService.save(account);
        user.setAccount(account);
        userService.save(user);
        return new UserDTO(user);
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/users/{id}")
    public UserDTO delete(@PathVariable(name = "id") Integer id) {
        return userService.delete(id);
    }

    @Operation(summary = "View profile of user logined")
    @GetMapping("/profile")
    public UserDTO profile(Principal principal) {
        AccountDTO accountDTO = accountService.findByUsername(principal.getName());
        UserDTO userDTO = accountDTO.getUserInfo();
        return userDTO;
    }
}
