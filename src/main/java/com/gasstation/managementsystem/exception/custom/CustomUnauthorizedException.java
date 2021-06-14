package com.gasstation.managementsystem.exception.custom;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CustomUnauthorizedException extends Exception {
    Map<String, String> errorHashMap;

    public CustomUnauthorizedException(String message) {
        errorHashMap = new HashMap<>();
        errorHashMap.put("error", message);
    }
}
