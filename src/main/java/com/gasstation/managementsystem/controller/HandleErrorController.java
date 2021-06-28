package com.gasstation.managementsystem.controller;

import com.gasstation.managementsystem.model.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class HandleErrorController {
    @GetMapping("/show-error")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, CustomError> showError(@RequestParam String code, @RequestParam String field, @RequestParam String message) {
        CustomError customError = CustomError.builder().code(code).field(field).message(message).build();
        Map<String, CustomError> map = new HashMap<>();
        map.put("error", customError);
        return map;
    }
}
