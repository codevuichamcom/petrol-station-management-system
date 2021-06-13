package com.gasstation.managementsystem.exception.custom;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CustomForbiddenException extends Exception {
    Map<String, Object> errorHashMap;

    public CustomForbiddenException(String message) {
        errorHashMap = new HashMap<>();
        errorHashMap.put("error", message);
    }

}
