package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.model.dto.AccountDTO;
import com.gasstation.managementsystem.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
@Tag(name = "Account", description = "API for Account")
public class AccountController {
    @Autowired
    AccountService accountService;
//    @Autowired
//    UserService userService;

    @Operation(summary = "View All Accounts")
    @GetMapping("/accounts")
    public List<AccountDTO> getAll() {
        return accountService.findAll();
    }

    @Operation(summary = "Get Account by id")
    @GetMapping("/account/{id}")
    public AccountDTO getOne(@PathVariable(name = "id") Integer id) {
        return accountService.findById(id);
    }

//    @PostMapping("/accounts")
//    public AccountDTO create(@Valid @RequestBody Account account){
//        UserDTO userDTO =  userService.save(account.getUserInfo());
//        account.setUserInfo(new User(userDTO));
//            return accountService.save(account);
//    }

}
