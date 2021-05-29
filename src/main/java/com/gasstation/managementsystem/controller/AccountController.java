package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.entity.Account;
import com.gasstation.managementsystem.model.dto.AccountDTO;
import com.gasstation.managementsystem.model.dto.AccountDTO;
import com.gasstation.managementsystem.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
@Tag(name = "Account", description = "API for Account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Operation(summary = "View All Accounts")
    @GetMapping("/accounts")
    public HashMap<String, Object> getAll(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                          @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return accountService.findAll(PageRequest.of(pageIndex - 1, pageSize));
    }

    @Operation(summary = "Get Account by id")
    @GetMapping("/account/{id}")
    public AccountDTO getOne(@PathVariable(name = "id") Integer id) {
        return accountService.findById(id);
    }

    @Operation(summary = "Create new account")
    @PostMapping("/accounts")
    public AccountDTO create(@Valid @RequestBody Account account) {
        return accountService.save(account);
    }

    @Operation(summary = "Update account by id")
    @PutMapping("/accounts/{id}")
    public AccountDTO update(@PathVariable(name = "id") Integer id, @Valid @RequestBody Account account) {
        account.setId(id);
        return accountService.save(account);
    }

    @Operation(summary = "Delete account by id")
    @DeleteMapping("/accounts/{id}")
    public AccountDTO delete(@PathVariable(name = "id") Integer id) {
        return accountService.delete(id);
    }

}
