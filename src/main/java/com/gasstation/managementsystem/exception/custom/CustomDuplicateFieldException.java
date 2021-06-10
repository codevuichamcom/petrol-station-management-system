package com.gasstation.managementsystem.exception.custom;

import com.gasstation.managementsystem.model.DuplicateError;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CustomDuplicateFieldException extends Exception {
    Map<String, DuplicateError> errorHashMap;

    public CustomDuplicateFieldException(String message, String field, String table) {
        errorHashMap = toMap(message, field, table);
    }

    public Map<String, DuplicateError> toMap(String message, String field, String table) {
        Map<String, DuplicateError> errorMap = new HashMap<>();
        errorMap.put("error", new DuplicateError(message, field, table));
        return errorMap;
    }

}
