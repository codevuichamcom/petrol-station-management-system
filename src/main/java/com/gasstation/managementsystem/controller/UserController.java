package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.model.dto.AccountDTO;
import com.gasstation.managementsystem.model.dto.UserDTO;
import com.gasstation.managementsystem.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
@Tag(name = "user")
public class UserController {
    @Autowired
    AccountService accountService;
    @GetMapping("/profile")
    public UserDTO profile(Principal principal) {
        AccountDTO accountDTO = accountService.findByUsername(principal.getName());
        UserDTO userDTO = accountDTO.getUserInfo();
        return userDTO;
    }
}
