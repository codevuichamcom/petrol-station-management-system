package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.model.dto.AccountDTO;
import com.gasstation.managementsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
public class MainController {
    @Autowired
    AccountService accountService;
    @GetMapping("/profile")
    public AccountDTO profile(Principal principal) {
        AccountDTO accountDTO = accountService.findByUsername(principal.getName());
        return accountDTO;
    }
}
