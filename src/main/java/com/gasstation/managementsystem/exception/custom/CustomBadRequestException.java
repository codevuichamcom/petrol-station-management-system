package com.gasstation.managementsystem.exception.custom;

import com.gasstation.managementsystem.model.CustomError;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CustomBadRequestException extends Exception {
    Map<String, CustomError> errorHashMap;

    public CustomBadRequestException(String message, String field, String table) {
        errorHashMap = toMap(message, field, table);
    }

    public Map<String, CustomError> toMap(String message, String field, String table) {
        Map<String, CustomError> errorMap = new HashMap<>();
        errorMap.put("error", new CustomError(message, field, table));
        return errorMap;
    }

}
