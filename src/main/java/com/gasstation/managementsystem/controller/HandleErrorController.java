package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.model.CustomError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HandleErrorController {
    @GetMapping("/show-error")
    public Map<String, CustomError> showError(@RequestParam String code, @RequestParam String field, @RequestParam String message) {
        CustomError customError = CustomError.builder().code(code).field(field).message(message).build();
        Map<String, CustomError> map = new HashMap<>();
        map.put("error", customError);
        return map;
    }
}
